package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.mapper.ProductMapper;
import com.catbytes.reviews.repository.CategoryRepository;
import com.catbytes.reviews.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findByNameAndBrand(product.getName(), product.getBrand());
        if (existingProduct.isPresent()) {
            throw new IllegalArgumentException("Product with the same name and brand already exists.");
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts(int limit, String sortBy, String direction) {
        // Determine the sorting direction
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        // Apply limit and sort
        return productRepository.findAll(PageRequest.of(0, limit, Sort.by(sortDirection, sortBy))).getContent();
    }


    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findByNameContainingIgnoreCase(String name, int limit, String sortBy, String direction) {
        // Determine the sorting direction
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        // Apply limit and sort
        return productRepository.findByNameContainingIgnoreCase(name, PageRequest.of(0, limit, Sort.by(sortDirection, sortBy))).getContent();
    }

    @Override
    public Double calculateAverageRating(Long productId) {
        //TODO: implement after [#11] - Review Entity and Review Posting API
        return null;
    }

    @Override
    public Product findOrCreateProduct(Product product) {
        if (product.getId() != null) {
            return productRepository.findProductById(product.getId())
                    .orElseGet(() -> {
                        LOG.warn("Product with id {} not found. Creating new product.", product.getId());
                        return addProduct(product);
                    });
        } else {
            LOG.warn("Product id not found. Creating new product.");
            return addProduct(product);
        }
    }

}