package com.ecommerce.productservice.repositories;

import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.projections.PriceDescProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);

    // JPA Query Methods
    List<Product> findByTitleAndPrice(String title, Double price);

    // HQL Query
    @Query("select p from Product p where p.id = :id")
    Product getProductDataHQL(@Param("id") Long id);

    // SQL Query
    @Query(value = "select * from product p where p.id = :id", nativeQuery = true)
    Product getProductDataSQL(@Param("id") Long id);

    // SQL Query
    @Query(value = "select price, description from product p where p.id = :id", nativeQuery = true)
    PriceDescProjection getProductDataSQLProjections(@Param("id") Long id);
}
