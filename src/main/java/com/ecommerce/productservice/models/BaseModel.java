package com.ecommerce.productservice.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @CreationTimestamp
    public Date createTimestamp;
    @UpdateTimestamp
    public Date updateTimestamp;
    @SoftDelete
    public boolean isDeleted;
}
