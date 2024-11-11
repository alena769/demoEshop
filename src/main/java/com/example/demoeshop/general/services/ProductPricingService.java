package com.example.demoeshop.general.services;

public interface ProductPricingService {
    double calculateDiscountedPrice(Long productId);
    void updatePrice(Long productId, double newPrice);
    void applyDiscount(Long productId, double discountPercentage);
}
