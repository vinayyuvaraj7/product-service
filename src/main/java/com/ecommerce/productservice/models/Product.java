package com.ecommerce.productservice.models;

import com.ecommerce.productservice.dtos.FakeStoreAPIRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageURL;
    private Category category;

    public FakeStoreAPIRequestDTO toFakeAPIRequestDTO() {
        return new FakeStoreAPIRequestDTO(
                title,
                description,
                price,
                imageURL,
                category.getName()
        );
    }
}
