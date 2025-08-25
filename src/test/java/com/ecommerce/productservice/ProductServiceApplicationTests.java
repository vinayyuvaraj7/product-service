package com.ecommerce.productservice;

import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testFindByTitleAndPrice() {
        List<Product> productList = productRepository
                .findByTitleAndPrice("Iphone", 80000.00);

        System.out.println(productList);
    }



}
