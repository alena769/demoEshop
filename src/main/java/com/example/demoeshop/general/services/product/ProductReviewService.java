package com.example.demoeshop.general.services.product;

public interface ProductReviewService {
    double getAverageRating(Long productId);
    void addRating(Long productId, double rating);
}
