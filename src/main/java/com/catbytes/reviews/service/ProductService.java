package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Product;

import java.util.List;

public interface ProductService {

    Double calculateAverageRating(Long productId);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product addProduct(Product product);

    void deleteProduct(Long id);

    List<Product> findByNameContainingIgnoreCase(String name);

}