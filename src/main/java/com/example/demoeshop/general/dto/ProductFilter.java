package com.example.demoeshop.general.dto;

import lombok.Data;

@Data
public class ProductFilter {
    private String category;
    private String brand;
    private Double minPrice;
    private Double maxPrice;
    private Double minRating;
    private Boolean isActive;
    private Integer minStock;
    private Double discount;
}
