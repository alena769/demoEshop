package com.example.demoeshop.general.services.product;

public interface ProductInventoryService {
    void adjustStock(Long productId, int quantityChange);
    int getStockLevel(Long productId);
    void setStockLevel(Long productId, int newStockLevel);
}
