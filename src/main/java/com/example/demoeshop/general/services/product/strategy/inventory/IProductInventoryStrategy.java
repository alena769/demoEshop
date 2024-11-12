package com.example.demoeshop.general.services.product.strategy.inventory;

import com.example.demoeshop.general.model.Product;

@FunctionalInterface
public interface IProductInventoryStrategy {
    void adjustStock(Product product, int quantityChange);
}
