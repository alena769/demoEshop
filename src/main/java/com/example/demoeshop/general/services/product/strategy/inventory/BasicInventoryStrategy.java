package com.example.demoeshop.general.services.product.strategy.inventory;

import com.example.demoeshop.general.model.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BasicInventoryStrategy implements IProductInventoryStrategy{
    @Override
    public void adjustStock(Product product, int quantityChange) {
        product.setStock(product.getStock() + quantityChange);
    }
}
