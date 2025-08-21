package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.dtos.CreateProductRequestDTO;
import com.ecommerce.productservice.dtos.ErrorDTO;
import com.ecommerce.productservice.dtos.UpdateProductRequestDTO;
import com.ecommerce.productservice.exceptions.ProductNotFoundException;
import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.services.FakeStoreAPIService;
import com.ecommerce.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.List;

@RestController
public class ProductServiceController {

    private ProductService productService;

    //Dependency Injection via constructor
    public ProductServiceController(FakeStoreAPIService fakeStoreAPIService) {
        this.productService = fakeStoreAPIService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    // Parent P = new Child();
    // Parent - getMessage();
    // Child - getMessage();
    // The getMessage() in the Child class is said be overridden.
    // When we are calling a overridden method, always the priority will be given to the Child class
    // If there is same method inside the parent and child, which method will be called?

    // ProductService ps = new ProductService(); Can I do this? No since Ps is an interface we cannot create an object.

    // ProductService ps = new FakeStoreAPIService();

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDTO requestDTO){
        return productService.createProduct(requestDTO.getTitle(),
                requestDTO.getDescription(),
                requestDTO.getPrice(),
                requestDTO.getImageURL(),
                requestDTO.getCategory());
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody UpdateProductRequestDTO updateRequestDTO) {
        Product product = updateRequestDTO.toProduct();
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.deleteProductById(id);
    }


    // Response Types
    // 1xx - 100, 101 etc., - Information Based Responses
    // 2xx - 200 - Success, 201 - Created, 204 - No Content etc.,
    // 3xx - Redirects
    // 4xx - 400 Bad Request, 404 Not Found, 403 Forbidden etc.,
    // 5xx - 500 Internal Server Error, 503 Service Unavailable etc.,

    //ISO-8601 Format

    //yyyyMMdd
    //yyyy-MM-dd - Date only Format
    //yyyy-MM-dd hh:mm:ss - Date Time Format
    //yyyy-MM-ddThh:mm:ss Date and Time divider
    //yyyy-MM-ddThh:mm:ss:MM.SSS
}
