package com.example.demoeshop.general.services.product.strategy.pricing;

import com.example.demoeshop.general.model.Product;

@FunctionalInterface
public interface IProductPricingStrategy {
    void applyPrice(Product product);
}
