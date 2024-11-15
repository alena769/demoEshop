package com.example.demoeshop.general.dto;

import com.example.demoeshop.general.model.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Schema(hidden = true)
public class ProductDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
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

    public static ProductDTO from(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory());
        dto.setBrand(product.getBrand());
        dto.setDiscount(product.getDiscount());
        dto.setIsActive(product.getIsActive());
        dto.setStock(product.getStock());
        dto.setPrice(product.getPrice());
        dto.setRating(product.getRating());
        return dto;
    }
}
