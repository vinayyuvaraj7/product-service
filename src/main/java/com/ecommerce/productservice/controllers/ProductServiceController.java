package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.dtos.CreateProductRequestDTO;
import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.services.FakeStoreAPIService;
import com.ecommerce.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductServiceController {

    private ProductService productService;

    //Dependency Injection via constructor
    public ProductServiceController(FakeStoreAPIService fakeStoreAPIService) {
        this.productService = fakeStoreAPIService;
    }

    public void getAllProducts(){
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") Long id){
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

    public void updateProduct(){

    }

    public void deleteProduct(Long id){

    }
}
