package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public Product saveProduct(Product product) {
        Category category = categoryService.getCategoryById(product.getCategory().getId());
        product.setCategory(category);
        // Дополнительная логика для сохранения продукта
        return productRepository.save(product);
    }

    public Product addProduct(Product product) {
        Category category = categoryService.getCategoryById(product.getCategory().getId());
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found for id: " + id));
    }
}
