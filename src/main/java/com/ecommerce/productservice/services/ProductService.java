package com.ecommerce.productservice.services;

import com.ecommerce.productservice.models.Product;

public interface ProductService {
    public Product getProductById(Long id);
    public Product createProduct(String title, String description, double price,
                                 String image, String category);
}
