package com.ecommerce.productservice.dtos;

import com.ecommerce.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDTO {
    private String title;
    private String description;
    private Double price;
    private String imageURL;
    private String category;
}
