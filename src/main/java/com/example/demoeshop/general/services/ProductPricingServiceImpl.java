package com.example.demoeshop.general.services;

import com.example.demoeshop.general.model.Product;
import com.example.demoeshop.general.repositories.ProductRepository;
import com.example.demoeshop.shared.exeption.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductPricingServiceImpl implements ProductPricingService {

    private final ProductRepository productRepository;

    @Override
    public double calculateDiscountedPrice(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return product.getPrice() * (1 - product.getDiscount() / 100);
    }

    @Override
    public void updatePrice(Long productId, double newPrice) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        product.setPrice(newPrice);
        productRepository.save(product);
    }

    @Override
    public void applyDiscount(Long productId, double discountPercentage) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        product.setDiscount(discountPercentage);
        productRepository.save(product);
    }
}
