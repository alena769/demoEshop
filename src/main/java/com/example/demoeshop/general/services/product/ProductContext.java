package com.example.demoeshop.general.services.product;

import com.example.demoeshop.general.model.Product;
import com.example.demoeshop.general.services.product.strategy.inventory.IProductInventoryStrategy;
import com.example.demoeshop.general.services.product.strategy.pricing.IProductPricingStrategy;
import com.example.demoeshop.general.services.product.strategy.review.IProductReviewStrategy;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
public class ProductContext {
    private IProductPricingStrategy pricingStrategy;
    private IProductInventoryStrategy inventoryStrategy;
    private IProductReviewStrategy reviewStrategy;

    public void applyPricing(Product product) {
        if (pricingStrategy != null) {
            pricingStrategy.applyPrice(product);
        }
    }

    public void adjustInventory(Product product, int quantityChange) {
        if (inventoryStrategy != null) {
            inventoryStrategy.adjustStock(product, quantityChange);
        }
    }

    public void addReview(Product product, double rating) {
        if (reviewStrategy != null) {
            reviewStrategy.addReview(product, rating);
        }
    }
}
