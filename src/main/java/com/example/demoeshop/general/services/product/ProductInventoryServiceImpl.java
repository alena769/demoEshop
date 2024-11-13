package com.example.demoeshop.general.services.product;

import com.example.demoeshop.general.model.Product;
import com.example.demoeshop.general.repositories.ProductRepository;
import com.example.demoeshop.general.exeption.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductInventoryServiceImpl implements ProductInventoryService {

    private final ProductRepository productRepository;

    @Override
    public int getStockLevel(Long productId) {
        return productRepository.findById(productId)
                .map(Product::getStock)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public void adjustStock(Long productId, int quantityChange) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        product.setStock(product.getStock() + quantityChange);
        productRepository.save(product);
    }

    @Override
    public void setStockLevel(Long productId, int newStockLevel) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        product.setStock(newStockLevel);
        productRepository.save(product);
    }
}
