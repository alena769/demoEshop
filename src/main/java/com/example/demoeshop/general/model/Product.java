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
    private String category;
    private String brand;
    private Double discount;
    private Boolean isActive;

    private Integer stock;
    private Double price;
    private Double rating;
}
