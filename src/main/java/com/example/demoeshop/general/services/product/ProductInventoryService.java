package com.example.demoeshop.general.services.product;

public interface ProductInventoryService {
    void adjustStock(Long productId, int quantityChange);
}
