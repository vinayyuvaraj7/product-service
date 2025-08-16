package com.ecommerce.productservice.services;

import com.ecommerce.productservice.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    public ResponseEntity<Product> getProductById(Long id);
    public Product createProduct(String title, String description, double price,
                                 String image, String category);
    public List<Product> getAllProducts();
    public ResponseEntity<Product> updateProduct(Long id, Product product);
}
