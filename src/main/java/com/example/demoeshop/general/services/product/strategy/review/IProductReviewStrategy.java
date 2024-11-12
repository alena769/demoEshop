package com.example.demoeshop.general.services.product.strategy.review;

import com.example.demoeshop.general.model.Product;

@FunctionalInterface
public interface IProductReviewStrategy {
    void addReview(Product product, double rating);
}
