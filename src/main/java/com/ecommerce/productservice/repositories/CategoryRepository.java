package com.ecommerce.productservice.repositories;

import com.ecommerce.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);
    Category save(Category category);
}
