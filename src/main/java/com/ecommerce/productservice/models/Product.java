package com.ecommerce.productservice.models;

import com.ecommerce.productservice.dtos.FakeStoreAPIRequestDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@ToString
public class Product extends BaseModel implements Serializable {
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

    public Long getId(){
        return this.id;
    }

    public void setCreateTimestamp(Date date){
        this.createTimestamp=date;
    }

    public Date getCreateTimestamp(){
        return this.createTimestamp;
    }

    public void setUpdateTimestamp(Date date){
        this.updateTimestamp=date;
    }

    public Date getUpdateTimestamp(){
        return this.updateTimestamp;
    }

    public void setIsDeleted(boolean isDeleted){
        this.isDeleted=isDeleted;
    }

    public boolean getIsDeleted(){
        return this.isDeleted;
    }
}
