package com.example.demoeshop.general.services.specification;
import com.example.demoeshop.general.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> hasCategory(String category) {
        return (root, query, criteriaBuilder) ->
                category == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("category"), category);
    }

    public static Specification<Product> hasBrand(String brand) {
        return (root, query, criteriaBuilder) ->
                brand == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("brand"), brand);
    }

    public static Specification<Product> hasMinPrice(Double minPrice) {
        return (root, query, criteriaBuilder) ->
                minPrice == null ? criteriaBuilder.conjunction() : criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> hasMaxPrice(Double maxPrice) {
        return (root, query, criteriaBuilder) ->
                maxPrice == null ? criteriaBuilder.conjunction() : criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Product> hasMinRating(Double minRating) {
        return (root, query, criteriaBuilder) ->
                minRating == null ? criteriaBuilder.conjunction() : criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), minRating);
    }

    public static Specification<Product> isActive(Boolean isActive) {
        return (root, query, criteriaBuilder) ->
                isActive == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("isActive"), isActive);
    }

    public static Specification<Product> hasStockGreaterThan(Integer stock) {
        return (root, query, criteriaBuilder) ->
                stock == null ? criteriaBuilder.conjunction() : criteriaBuilder.greaterThanOrEqualTo(root.get("stock"), stock);
    }

    public static Specification<Product> hasDiscount(Double discount) {
        return (root, query, criteriaBuilder) ->
                discount == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("discount"), discount);
    }
}

