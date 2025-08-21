package com.ecommerce.productservice.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel {
    @Id
    private Long id;
    private String createTimestamp;
    private String updateTimestamp;
    private boolean isDeleted;
}
