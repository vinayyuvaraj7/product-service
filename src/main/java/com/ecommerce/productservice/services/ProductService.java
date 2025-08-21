package com.ecommerce.productservice.services;

import com.ecommerce.productservice.dtos.FakeStoreAPIResponseDTO;
import com.ecommerce.productservice.dtos.UpdateProductRequestDTO;
import com.ecommerce.productservice.exceptions.ProductNotFoundException;
import com.ecommerce.productservice.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    public ResponseEntity<Product> getProductById(Long id) throws ProductNotFoundException;
    public Product createProduct(String title, String description, double price,
                                 String image, String category);
    public List<Product> getAllProducts();
    public Product updateProduct(Long id, Product product);
    public ResponseEntity<Product> deleteProductById(Long id) throws ProductNotFoundException;
}
