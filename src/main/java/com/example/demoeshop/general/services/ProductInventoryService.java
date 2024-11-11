package com.example.demoeshop.general.services;

public interface ProductInventoryService {
    int getStockLevel(Long productId);
    void adjustStock(Long productId, int quantityChange);
    void setStockLevel(Long productId, int newStockLevel);
}
