package com.ecommerce.productservice.models;

import com.ecommerce.productservice.dtos.FakeStoreAPIRequestDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class Product extends BaseModel {
    private String title;
    private String description;
    private Double price;
    private String imageURL;
    @ManyToOne (cascade = CascadeType.PERSIST)
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

    public void setId(Long id){
        this.id=id;
    }
}
