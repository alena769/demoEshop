package com.example.demoeshop.general.controllers;

import com.example.demoeshop.general.dto.ProductDTO;
import com.example.demoeshop.general.dto.ProductFilter;
import com.example.demoeshop.general.model.Product;
import com.example.demoeshop.general.services.product.ProductService;
import com.example.demoeshop.general.services.product.ProductInventoryService;
import com.example.demoeshop.general.services.product.ProductReviewService;
import com.example.demoeshop.general.exeption.ProductNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Product", description = "APIs for managing products")
public class ProductController {

    private final ProductService productService;
    private final ProductInventoryService productInventoryService;
    private final ProductReviewService productReviewService;

    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieve a list of all available products.")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieve product details by its ID.")
    public ResponseEntity<Product> getProductById(
            @Parameter(description = "ID of the product to retrieve", required = true)
            @PathVariable
            Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Add a new product", description = "Create and add a new product to the inventory.")
    public Product addProduct(
            @Parameter(description = "Product details for the new product", required = true)
            @RequestBody
            Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product", description = "Update product details by its ID.")
    public ResponseEntity<Product> updateProduct(
            @Parameter(description = "ID of the product to update", required = true)
            @PathVariable
            Long id,
            @Parameter(description = "Updated product details", required = true)
            @RequestBody
            Product productDetails) {
        return ResponseEntity.ok(productService.updateProduct(id, productDetails));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Remove a product from the inventory by its ID.")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "ID of the product to delete", required = true)
            @PathVariable
            Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/filter")
    @Operation(summary = "Filter products", description = "Filter products based on various criteria.")
    public List<Product> filterProducts(
            @Parameter(description = "Filter criteria for products")
            @RequestBody
            ProductFilter filter) {
        return productService.filterProducts(filter);
    }

    @PostMapping("/{id}/adjustStock")
    @Operation(summary = "Adjust product stock", description = "Adjust the stock quantity for a product.")
    public void adjustStock(
            @Parameter(description = "ID of the product to adjust stock for", required = true)
            @PathVariable
            Long id,
            @Parameter(description = "Change in quantity", required = true)
            @RequestParam
            int quantityChange) {
        productInventoryService.adjustStock(id, quantityChange);
    }

    @PostMapping("/{id}/applyDiscount")
    @Operation(summary = "Apply discount to a product", description = "Apply a discount to the product price.")
    public ResponseEntity<ProductDTO> applyDiscount(
            @Parameter(description = "ID of the product to apply discount", required = true)
            @PathVariable
            Long id,
            @Parameter(description = "Discount amount to apply", required = true)
            @RequestParam
            double discountAmount) {

        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        productService.applyDiscount(product, discountAmount);

        return ResponseEntity.ok(ProductDTO.from(product));
    }

    @PostMapping("/{id}/adjustInventory")
    @Operation(summary = "Adjust product inventory", description = "Adjust inventory for the specified product.")
    public ResponseEntity<ProductDTO> adjustInventory(
            @Parameter(description = "ID of the product to adjust inventory for", required = true)
            @PathVariable
            Long id,
            @Parameter(description = "Change in stock quantity", required = true)
            @RequestParam
            int quantityChange) {

        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        productService.adjustInventory(product, quantityChange);

        return ResponseEntity.ok(ProductDTO.from(product));
    }

    @PostMapping("/{id}/addReview")
    @Operation(summary = "Add a product review", description = "Add a rating review for the specified product.")
    public ResponseEntity<ProductDTO> addRating(
            @Parameter(description = "ID of the product to add a review to", required = true)
            @PathVariable
            Long id,
            @Parameter(description = "Rating value for the product review", required = true)
            @RequestParam
            double rating) {

        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        productService.addReview(product, rating);

        return ResponseEntity.ok(ProductDTO.from(product));
    }

    @GetMapping("/{id}/rating")
    @Operation(summary = "Get average product rating",
            description = "Get the average rating for the specified product.")
    public double getAverageRating(
            @Parameter(description = "ID of the product to get the average rating for", required = true)
            @PathVariable
            Long id) {
        return productReviewService.getAverageRating(id);
    }
}
