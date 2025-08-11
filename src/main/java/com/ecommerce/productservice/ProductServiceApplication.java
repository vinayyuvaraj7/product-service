package com.ecommerce.productservice;

import com.ecommerce.productservice.controllers.ProductServiceController;
import com.ecommerce.productservice.models.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
