package com.catbytes.reviews.service;

import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.entity.Brand;
import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findByNameContainingIgnoreCase(String name, int limit, String sortBy, String direction);

    Double setAverageRating(Long productId);

    List<Product> getAllProducts(int limit, String sortBy, String direction);

    Product getProductById(Long id);

    Product addProduct(Product product);

    void deleteProduct(Long id);

    Product findOrCreateProduct(Product product);

    Category findCategoryById(Long categoryId);

    Brand findOrCreateBrand(Brand brand);

    List<Brand> getAllBrands(String sortBy, String direction, int limit);

    List<Brand> findBrandsByNameContainingIgnoreCase(String name, int limit, String sortBy, String direction);

}