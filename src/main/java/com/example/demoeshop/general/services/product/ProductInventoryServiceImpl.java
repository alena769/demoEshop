package com.example.demoeshop.general.services.product;

import com.example.demoeshop.general.model.Product;
import com.example.demoeshop.general.repositories.ProductRepository;
import com.example.demoeshop.general.exeption.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductInventoryServiceImpl implements ProductInventoryService {

    private final ProductRepository productRepository;

    @Override
    public int getStockLevel(Long productId) {
        log.debug("Fetching stock level for product ID: {}", productId);
        return productRepository.findById(productId)
                .map(Product::getStock)
                .orElseThrow(() -> {
                    log.error("Product with ID {} not found", productId);
                    return new ProductNotFoundException("Product not found");
                });
    }

    @Override
    public void adjustStock(Long productId, int quantityChange) {
        log.debug("Adjusting stock for product ID: {} by {}", productId, quantityChange);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.error("Product with ID {} not found", productId);
                    return new ProductNotFoundException("Product not found");
                });

        int updatedStock = product.getStock() + quantityChange;
        product.setStock(updatedStock);
        productRepository.save(product);
        log.info("Stock for product ID {} adjusted by {}. New stock level: {}", productId, quantityChange, updatedStock);
    }

    @Override
    public void setStockLevel(Long productId, int newStockLevel) {
        log.debug("Setting stock level for product ID: {} to {}", productId, newStockLevel);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.error("Product with ID {} not found", productId);
                    return new ProductNotFoundException("Product not found");
                });

        product.setStock(newStockLevel);
        productRepository.save(product);
        log.info("Stock level for product ID {} set to {}", productId, newStockLevel);
    }
}
