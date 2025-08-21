package com.ecommerce.productservice.models;

import com.ecommerce.productservice.dtos.FakeStoreAPIRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private Double price;
    private String imageURL;
    @ManyToOne
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
