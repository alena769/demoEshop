package com.example.demoeshop.general.services.product.strategy.pricing;

import com.example.demoeshop.general.model.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FixedDiscountPricingStrategy implements IProductPricingStrategy {
    private final double discountAmount;

    @Override
    public void applyPrice(Product product) {
        product.setPrice(product.getPrice() - discountAmount);

    }
}
