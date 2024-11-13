package com.example.demoeshop.general.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(hidden = true)
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
