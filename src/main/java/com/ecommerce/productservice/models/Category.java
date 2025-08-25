package com.ecommerce.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Category extends BaseModel {
    private String name;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    @ToString.Exclude
    private List<Product> products;
}
