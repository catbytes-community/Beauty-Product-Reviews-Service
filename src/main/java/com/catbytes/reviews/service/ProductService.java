package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Double calculateAverageRating(Long productId);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product saveProduct(Product product);

    void deleteProduct(Long id);

    Optional<Product> findByNameContainingIgnoreCase(String name);

}