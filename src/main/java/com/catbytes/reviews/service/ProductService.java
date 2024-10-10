package com.catbytes.reviews.service;

import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Double calculateAverageRating(Long productId);

    List<Product> getAllProducts();
    
    Product addProduct(ProductDTO productDTO);

    void deleteProduct(Long id);

    Optional<Product> findByName(String name);

    //TODO: ass public methods (add, delete etc)
}