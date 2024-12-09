package com.catbytes.reviews.service;

import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.entity.Brand;
import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findByNameContainingIgnoreCase(String name, int limit, String sortBy, String direction);

    Double calculateAverageRating(Long productId);

    List<Product> getAllProducts(int limit, String sortBy, String direction);

    Product getProductById(Long id);

    Product addProduct(ProductDTO productDTO);

    void deleteProduct(Long id);

    Category findCategoryById(Long categoryId);

    Brand findOrCreateBrand(Brand brand);

    List<Brand> getAllBrands(String sortBy, String direction, int limit);

    List<Brand> findBrandsByNameContainingIgnoreCase(String name, int limit, String sortBy, String direction);
}