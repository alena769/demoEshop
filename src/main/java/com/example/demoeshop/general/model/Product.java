package com.example.demoeshop.general.model;

import com.example.demoeshop.shared.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Product extends BaseEntity<Long> {
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
    private String brand;
    private Double rating;
    private Double discount;
    private Boolean isActive;
}
