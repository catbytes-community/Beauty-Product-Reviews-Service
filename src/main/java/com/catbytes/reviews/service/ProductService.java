package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.entity.Brand;

import java.util.List;

public interface ProductService {

    List<Product> findByNameContainingIgnoreCase(String name, int limit, String sortBy, String direction);

    Double calculateAverageRating(Long productId);

    List<Product> getAllProducts(int limit, String sortBy, String direction);

    Product getProductById(Long id);

    Product addProduct(Product product);

    void deleteProduct(Long id);

    // Methods for Brand
    List<Brand> getAllBrands();

    Brand addBrand(String brandName);

}