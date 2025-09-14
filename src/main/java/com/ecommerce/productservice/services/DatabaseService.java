package com.ecommerce.productservice.services;

import com.ecommerce.productservice.exceptions.ProductNotFoundException;
import com.ecommerce.productservice.models.Category;
import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.repositories.CategoryRepository;
import com.ecommerce.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("databaseService")
public class DatabaseService implements ProductService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private RedisTemplate<String, Object> redisTemplate;

    public DatabaseService(CategoryRepository categoryRepository,
                           ProductRepository productRepository,
                           RedisTemplate<String, Object> redisTemplate) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ResponseEntity<Product> getProductById(Long id) throws ProductNotFoundException {

        Product productFromRedis = (Product) redisTemplate.opsForValue().get(String.valueOf(id));

        if(productFromRedis != null) {
            return new ResponseEntity<>(productFromRedis, HttpStatusCode.valueOf(200));
        }

        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isPresent()) {
            redisTemplate.opsForValue().set(String.valueOf(id), productOptional.get());
            return ResponseEntity.ok(productOptional.get());
        } else {
            throw new ProductNotFoundException("Product not found");
        }
    }

    @Override
    public Product createProduct(Product product) {
        // Before inserting into the Product, we need to know the Category Id.
        Category categoryFromDB = categoryRepository.findByName(product.getCategory().getName());

        if(categoryFromDB == null){
            Category newCategory = new Category();
            newCategory.setName(product.getCategory().getName());
            categoryFromDB =  categoryRepository.save(newCategory);
        }
        product.setCategory(categoryFromDB);

        Product productResponse = productRepository.save(product);

        return productResponse;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product>  productList = productRepository.findAll(pageable);
        return productList;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        // Check if the category is present or not.
        Category categoryFromDB = categoryRepository.findByName(product.getCategory().getName());

        // If the category is not present, create a new record and insert into the DB.
        if(categoryFromDB == null){
            Category newCategory = new Category();
            newCategory.setName(product.getCategory().getName());
            categoryFromDB = newCategory;
        }
        product.setCategory(categoryFromDB);

        return productRepository.save(product);
    }

    @Override
    public ResponseEntity<Product> deleteProductById(Long id) throws ProductNotFoundException {
        // Check if the product with the given id is present in the Database.

        Optional<Product> productOptional = productRepository.findById(id);
        // If it is present then delete the data and return the product value.
        if(productOptional.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok(productOptional.get());
        }

        // If it is not present, then throw an exception.
        throw new ProductNotFoundException("Product not found");
    }
}
