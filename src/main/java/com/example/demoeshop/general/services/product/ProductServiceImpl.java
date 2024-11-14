package com.example.demoeshop.general.services.product;

import com.example.demoeshop.general.dto.ProductFilter;
import com.example.demoeshop.general.model.Product;
import com.example.demoeshop.general.repositories.ProductRepository;
import com.example.demoeshop.general.services.product.strategy.ProductStrategyFactory;
import com.example.demoeshop.general.services.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductStrategyFactory strategyFactory;

    @Override
    public List<Product> getAllProducts() {
        log.debug("Fetching all products from the database");
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        log.debug("Fetching product with ID: {}", id);
        return productRepository.findById(id);
    }

    @Override
    public Product addProduct(Product product) {
        log.debug("Adding a new product: {}", product);
        Product savedProduct = productRepository.save(product);
        log.info("Product added successfully with ID: {}", savedProduct.getId());
        return savedProduct;
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        log.debug("Updating product with ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product with ID {} not found", id);
                    return new RuntimeException("Product not found");
                });

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        product.setCategory(productDetails.getCategory());
        product.setBrand(productDetails.getBrand());
        product.setRating(productDetails.getRating());
        product.setDiscount(productDetails.getDiscount());
        product.setIsActive(productDetails.getIsActive());

        Product updatedProduct = productRepository.save(product);
        log.info("Product with ID {} updated successfully", updatedProduct.getId());
        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        log.debug("Deleting product with ID: {}", id);
        productRepository.deleteById(id);
        log.info("Product with ID {} deleted successfully", id);
    }

    @Override
    public List<Product> filterProducts(ProductFilter productFilter) {
        log.debug("Filtering products with filter criteria: {}", productFilter);

        Specification<Product> spec = Specification.where(ProductSpecification.hasCategory(productFilter.getCategory()))
                .and(ProductSpecification.hasBrand(productFilter.getBrand()))
                .and(ProductSpecification.hasMinPrice(productFilter.getMinPrice()))
                .and(ProductSpecification.hasMaxPrice(productFilter.getMaxPrice()))
                .and(ProductSpecification.hasMinRating(productFilter.getMinRating()))
                .and(ProductSpecification.isActive(productFilter.getIsActive()))
                .and(ProductSpecification.hasStockGreaterThan(productFilter.getMinStock()))
                .and(ProductSpecification.hasDiscount(productFilter.getDiscount()));

        List<Product> filteredProducts = productRepository.findAll(spec);
        log.info("Found {} products matching filter criteria", filteredProducts.size());
        return filteredProducts;
    }

    @Override
    public void applyDiscount(Product product, double discountAmount) {
        log.debug("Applying discount of {} to product with ID: {}", discountAmount, product.getId());

        ProductContext productContext = new ProductContext();
        productContext.setPricingStrategy(strategyFactory.getPricingStrategy(discountAmount));

        productContext.applyPricing(product);
        productRepository.save(product);

        log.info("Discount applied to product with ID: {}", product.getId());
    }

    @Override
    public void adjustInventory(Product product, int quantityChange) {
        log.debug("Adjusting inventory for product with ID: {} by quantity: {}", product.getId(), quantityChange);

        ProductContext productContext = new ProductContext();
        productContext.setInventoryStrategy(strategyFactory.getInventoryStrategy());

        productContext.adjustInventory(product, quantityChange);
        productRepository.save(product);

        log.info("Inventory adjusted for product with ID: {}", product.getId());
    }

    @Override
    public void addReview(Product product, double rating) {
        log.debug("Adding review with rating {} to product with ID: {}", rating, product.getId());

        ProductContext productContext = new ProductContext();
        productContext.setReviewStrategy(strategyFactory.getReviewStrategy());

        productContext.addReview(product, rating);
        productRepository.save(product);

        log.info("Review added to product with ID: {}", product.getId());
    }
}
