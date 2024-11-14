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
public class ProductReviewServiceImpl implements ProductReviewService {

    private final ProductRepository productRepository;

    @Override
    public double getAverageRating(Long productId) {
        log.debug("Fetching average rating for product with ID: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.error("Product with ID {} not found", productId);
                    return new ProductNotFoundException("Product not found");
                });
        log.info("Fetched average rating for product ID {}: {}", productId, product.getRating());
        return product.getRating();
    }

    @Override
    public void addRating(Long productId, double rating) {
        log.debug("Adding rating of {} to product with ID: {}", rating, productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.error("Product with ID {} not found", productId);
                    return new ProductNotFoundException("Product not found");
                });

        double newRating = (product.getRating() + rating) / 2; // Simple average calculation
        product.setRating(newRating);
        productRepository.save(product);

        log.info("New average rating of {} set for product ID {}", newRating, productId);
    }
}
