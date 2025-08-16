package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.dtos.CreateProductRequestDTO;
import com.ecommerce.productservice.dtos.UpdateProductRequestDTO;
import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.services.FakeStoreAPIService;
import com.ecommerce.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDTO requestDTO){
        return productService.createProduct(requestDTO.getTitle(),
                requestDTO.getDescription(),
                requestDTO.getPrice(),
                requestDTO.getImageURL(),
                requestDTO.getCategory());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody UpdateProductRequestDTO updateRequest){
        ResponseEntity<Product> responseEntity = productService.updateProduct(id, updateRequest.toProduct());
        return responseEntity;
    }

    public void deleteProduct(Long id){

    }


    // Response Types
    // 1xx - 100, 101 etc., - Information Based Responses
    // 2xx - 200 - Success, 201 - Created, 204 - No Content etc.,
    // 3xx - Redirects
    // 4xx - 400 Bad Request, 404 Not Found, 403 Forbidden etc.,
    // 5xx - 500 Internal Server Error, 503 Service Unavailable etc.,
}
