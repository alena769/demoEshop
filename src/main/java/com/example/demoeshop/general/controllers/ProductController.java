package com.example.demoeshop.general.controllers;

import com.example.demoeshop.general.dto.ProductDTO;
import com.example.demoeshop.general.dto.ProductFilter;
import com.example.demoeshop.general.model.Product;
import com.example.demoeshop.general.services.product.ProductService;
import com.example.demoeshop.general.services.product.ProductInventoryService;
import com.example.demoeshop.general.services.product.ProductPricingService;
import com.example.demoeshop.general.services.product.ProductReviewService;
import com.example.demoeshop.shared.exeption.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductInventoryService productInventoryService;
    private final ProductPricingService productPricingService;
    private final ProductReviewService productReviewService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable
            Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Product addProduct(
            @RequestBody
            Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable
            Long id,
            @RequestBody
            Product productDetails) {
        return ResponseEntity.ok(productService.updateProduct(id, productDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable
            Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/filter")
    public List<Product> filterProducts(@RequestBody ProductFilter filter) {
        return productService.filterProducts(filter);
    }

    @PostMapping("/{id}/adjustStock")
    public void adjustStock(@PathVariable Long id, @RequestParam int quantityChange) {
        productInventoryService.adjustStock(id, quantityChange);
    }

    @PostMapping("/{id}/applyDiscount")
    public ResponseEntity<ProductDTO> applyDiscount(
            @PathVariable Long id,
            @RequestParam double discountAmount) {

        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        productService.applyDiscount(product, discountAmount);

        return ResponseEntity.ok(ProductDTO.from(product));
    }

    @PostMapping("/{id}/adjustInventory")
    public ResponseEntity<ProductDTO> adjustInventory(
            @PathVariable Long id,
            @RequestParam int quantityChange) {

        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        productService.adjustInventory(product, quantityChange);

        return ResponseEntity.ok(ProductDTO.from(product));
    }

    @PostMapping("/{id}/addReview")
    public ResponseEntity<ProductDTO> addRating(
            @PathVariable Long id,
            @RequestParam double rating) {

        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        productService.addReview(product, rating);

        return ResponseEntity.ok(ProductDTO.from(product));
    }

    @GetMapping("/{id}/rating")
    public double getAverageRating(
            @PathVariable
            Long id) {
        return productReviewService.getAverageRating(id);
    }
}
