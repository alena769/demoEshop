package com.example.demoeshop.general.services.product;

import com.example.demoeshop.general.dto.ProductFilter;
import com.example.demoeshop.general.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product addProduct(Product product);
    List<Product> filterProducts(ProductFilter filter);
    void deleteProduct(Long id);
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    Product updateProduct(Long id, Product productDetails);
}
