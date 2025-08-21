package com.ecommerce.productservice.dtos;

import com.ecommerce.productservice.models.Category;
import com.ecommerce.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateProductRequestDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageURL;
    private String category;

    public Product toProduct() {
        Product product = new Product();
//        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageURL(imageURL);

        Category categoryObj = new Category();
        categoryObj.setName(category);

        product.setCategory(categoryObj);

        return product;
    }
}
