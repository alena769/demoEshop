package com.example.demoeshop.general.services.product;

public interface ProductPricingService {
    double calculateDiscountedPrice(Long productId);
    void updatePrice(Long productId, double newPrice);
}
