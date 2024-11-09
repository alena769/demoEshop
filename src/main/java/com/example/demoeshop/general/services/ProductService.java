package com.example.demoeshop.general.services;

import com.example.demoeshop.general.model.Product;
import com.example.demoeshop.general.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

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

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> filterProducts(
            String category, String brand, Double minPrice, Double maxPrice,
            Double minRating, Boolean isActive, Integer minStock, Double discount) {

        Specification<Product> spec = Specification.where(ProductSpecification.hasCategory(category))
                .and(ProductSpecification.hasBrand(brand))
                .and(ProductSpecification.hasMinPrice(minPrice))
                .and(ProductSpecification.hasMaxPrice(maxPrice))
                .and(ProductSpecification.hasMinRating(minRating))
                .and(ProductSpecification.isActive(isActive))
                .and(ProductSpecification.hasStockGreaterThan(minStock))
                .and(ProductSpecification.hasDiscount(discount));

        return productRepository.findAll(spec);
    }
}
