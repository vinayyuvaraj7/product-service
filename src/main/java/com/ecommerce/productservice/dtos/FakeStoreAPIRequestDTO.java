package com.ecommerce.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FakeStoreAPIRequestDTO {
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
}
