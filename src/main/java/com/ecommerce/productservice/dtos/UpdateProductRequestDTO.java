package com.ecommerce.productservice.dtos;


import com.ecommerce.productservice.models.Category;
import com.ecommerce.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequestDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;

    public Product toProduct() {
        Product product = new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setDescription(getDescription());
        product.setPrice(getPrice());
        product.setImageURL(getImage());

        Category category = new Category();
        category.setName(getCategory());

        product.setCategory(category);

        return product;
    }
}
