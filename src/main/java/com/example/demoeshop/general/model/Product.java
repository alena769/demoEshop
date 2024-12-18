package com.example.demoeshop.general.model;

import com.example.demoeshop.general.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "products")
public class Product extends BaseEntity<Long> {

    @NotNull
    private String name;
    private String description;
    private String category;
    private String brand;
    private Double discount;

    @NotNull
    private Boolean isActive;

    @NotNull
    private Integer stock;
    @NotNull
    private Double price;
    private Double rating;
}
