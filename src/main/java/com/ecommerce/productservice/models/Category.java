package com.ecommerce.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel implements Serializable {
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @JsonIgnore
    @Fetch(FetchMode.SELECT)
    private List<Product> products;
    private int quantity;
}
