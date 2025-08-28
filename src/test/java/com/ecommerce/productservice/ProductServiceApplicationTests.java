package com.ecommerce.productservice;

import com.ecommerce.productservice.models.Category;
import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.projections.PriceDescProjection;
import com.ecommerce.productservice.repositories.CategoryRepository;
import com.ecommerce.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testFindByTitleAndPrice() {
        List<Product> productList = productRepository
                .findByTitleAndPrice("Iphone", 80000.00);

        System.out.println(productList);
    }

    @Test
    @Transactional
    void testFindById() {
        Optional<Category> category = categoryRepository.findById(1L);
        System.out.println(category.get().getName());
        System.out.println(category.get().getProducts());
    }

    @Test
    void testGetProductDataHQL() {
        Product product = productRepository
                .getProductDataHQL(1L);

        System.out.println(product);
    }

    @Test
    void testGetProductDataSQL() {
        Product product = productRepository
                .getProductDataSQL(52L);

        System.out.println(product);
    }

    @Test
    void testProjections() {
        PriceDescProjection priceDesc = productRepository
                .getProductDataSQLProjections(52L);

        System.out.println("Price: " + priceDesc.getPrice()
                + ", Description: " + priceDesc.getDescription());
    }

    @Test
    @Transactional
    void testLazyTypes() {
        Optional<Category> category = categoryRepository
                .findById(1L);

        System.out.println(category.get().getProducts());
    }

    @Test
    @Transactional
    void testNPlusOneProblem() {
        List<Category> categories = categoryRepository
                .findAll();

        for(Category category :  categories) {
            for(Product product : category.getProducts()) {
                System.out.println(product);
            }
        }
    }
}
