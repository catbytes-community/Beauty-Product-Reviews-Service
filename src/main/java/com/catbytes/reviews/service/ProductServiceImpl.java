package com.catbytes.reviews.service;

import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.entity.Category;
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
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
                              ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;

    }

    @Override
    public Product addProduct(ProductDTO productDTO) {
        Optional<Product> existingProduct = productRepository.findByNameAndBrand(productDTO.getName(), productDTO.getBrand());
        if (existingProduct.isPresent()) {
            throw new IllegalArgumentException("Product with the same name and brand already exists.");
        }
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        Product product = productMapper.toEntity(productDTO, category);
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
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }
    //TODO: add find by Name

    //TODO: remove product

    @Override
    public Double calculateAverageRating(Long productId) {
        //TODO: implement after [#11] - Review Entity and Review Posting API
        return null;
    }

}