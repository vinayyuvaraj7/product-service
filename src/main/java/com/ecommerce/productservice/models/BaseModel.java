package com.ecommerce.productservice.models;

import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public Date createTimestamp;
    public Date updateTimestamp;
    public boolean isDeleted;
}
