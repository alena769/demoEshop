package com.example.demoeshop.general.services.product.strategy.pricing;

import com.example.demoeshop.general.model.Product;
import com.example.demoeshop.general.services.product.strategy.pricing.IProductPricingStrategy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PercentageDiscountPricingStrategy implements IProductPricingStrategy {
    private final double discountPercentage;
    @Override
    public void applyPrice(Product product) {
        double discount = product.getPrice() * (discountPercentage / 100);
        product.setPrice(product.getPrice() - discount);
    }
}
