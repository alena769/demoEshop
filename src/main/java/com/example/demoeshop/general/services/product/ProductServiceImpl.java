package com.example.demoeshop.general.services.product;

import com.example.demoeshop.general.dto.ProductFilter;
import com.example.demoeshop.general.model.Product;
import com.example.demoeshop.general.repositories.ProductRepository;
import com.example.demoeshop.general.services.product.strategy.ProductStrategyFactory;
import com.example.demoeshop.general.services.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductStrategyFactory strategyFactory;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        product.setCategory(productDetails.getCategory());
        product.setBrand(productDetails.getBrand());
        product.setRating(productDetails.getRating());
        product.setDiscount(productDetails.getDiscount());
        product.setIsActive(productDetails.getIsActive());

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> filterProducts(ProductFilter productFilter) {

        Specification<Product> spec = Specification.where(ProductSpecification.hasCategory(productFilter.getCategory()))
                .and(ProductSpecification.hasBrand(productFilter.getBrand()))
                .and(ProductSpecification.hasMinPrice(productFilter.getMinPrice()))
                .and(ProductSpecification.hasMaxPrice(productFilter.getMaxPrice()))
                .and(ProductSpecification.hasMinRating(productFilter.getMinRating()))
                .and(ProductSpecification.isActive(productFilter.getIsActive()))
                .and(ProductSpecification.hasStockGreaterThan(productFilter.getMinStock()))
                .and(ProductSpecification.hasDiscount(productFilter.getDiscount()));

        return productRepository.findAll(spec);
    }

    @Override
    public void applyDiscount(Product product, double discountAmount) {
        ProductContext productContext = new ProductContext();
        productContext.setPricingStrategy(strategyFactory.getPricingStrategy(discountAmount));

        productContext.applyPricing(product);
        productRepository.save(product);
    }

    @Override
    public void adjustInventory(Product product, int quantityChange) {
        ProductContext productContext = new ProductContext();
        productContext.setInventoryStrategy(strategyFactory.getInventoryStrategy());

        productContext.adjustInventory(product, quantityChange);
        productRepository.save(product);
    }

    @Override
    public void addReview(Product product, double rating) {
        ProductContext productContext = new ProductContext();
        productContext.setReviewStrategy(strategyFactory.getReviewStrategy());

        // Apply review strategy
        productContext.addReview(product, rating);
        productRepository.save(product);
    }

}
