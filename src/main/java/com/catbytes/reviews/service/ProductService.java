package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findByNameContainingIgnoreCase(String name, int limit, String sortBy, String direction);

    Double calculateAverageRating(Long productId);

    List<Product> getAllProducts(int limit, String sortBy, String direction);

    Product getProductById(Long id);

    Product addProduct(Product product);

    void deleteProduct(Long id);

    Product findOrCreateProduct(Product product);
}