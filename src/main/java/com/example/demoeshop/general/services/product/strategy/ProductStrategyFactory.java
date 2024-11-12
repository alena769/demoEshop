package com.example.demoeshop.general.services.product.strategy;

import com.example.demoeshop.general.services.product.strategy.inventory.BasicInventoryStrategy;
import com.example.demoeshop.general.services.product.strategy.inventory.IProductInventoryStrategy;
import com.example.demoeshop.general.services.product.strategy.pricing.FixedDiscountPricingStrategy;
import com.example.demoeshop.general.services.product.strategy.pricing.IProductPricingStrategy;
import com.example.demoeshop.general.services.product.strategy.review.BasicReviewStrategy;
import com.example.demoeshop.general.services.product.strategy.review.IProductReviewStrategy;
import org.springframework.stereotype.Component;

@Component
public class ProductStrategyFactory {
    public IProductPricingStrategy getPricingStrategy(double discountAmount) {
        return new FixedDiscountPricingStrategy(discountAmount);
    }

    public IProductInventoryStrategy getInventoryStrategy() {
        return new BasicInventoryStrategy();
    }

    public IProductReviewStrategy getReviewStrategy() {
        return new BasicReviewStrategy();
    }
}
