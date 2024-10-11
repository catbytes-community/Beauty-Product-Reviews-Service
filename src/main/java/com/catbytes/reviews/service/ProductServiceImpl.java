package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.mapper.ProductMapper;
import com.catbytes.reviews.repository.CategoryRepository;
import com.catbytes.reviews.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
                              ProductMapper productMapper) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findByNameAndBrand(product.getName(), product.getBrand());
        if (existingProduct.isPresent()) {
            throw new IllegalArgumentException("Product with the same name and brand already exists.");
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {  // Реализуем метод
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findByNameContainingIgnoreCase(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Double calculateAverageRating(Long productId) {
        //TODO: implement after [#11] - Review Entity and Review Posting API
        return null;
    }

}