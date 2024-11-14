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
public class ProductPricingServiceImpl implements ProductPricingService {

    private final ProductRepository productRepository;

    @Override
    public double calculateDiscountedPrice(Long productId) {
        log.debug("Calculating discounted price for product with ID: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.error("Product with ID {} not found", productId);
                    return new ProductNotFoundException("Product not found");
                });
        double discountedPrice = product.getPrice() * (1 - product.getDiscount() / 100);
        log.info("Discounted price for product ID {} is calculated as {}", productId, discountedPrice);
        return discountedPrice;
    }

    @Override
    public void updatePrice(Long productId, double newPrice) {
        log.debug("Updating price for product with ID: {} to new price: {}", productId, newPrice);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.error("Product with ID {} not found", productId);
                    return new ProductNotFoundException("Product not found");
                });
        product.setPrice(newPrice);
        productRepository.save(product);
        log.info("Price for product ID {} updated to {}", productId, newPrice);
    }
}
