package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.entity.Brand;
import com.catbytes.reviews.repository.BrandRepository;
import com.catbytes.reviews.mapper.ProductMapper;
import com.catbytes.reviews.repository.CategoryRepository;
import com.catbytes.reviews.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
                              ProductMapper productMapper, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;

    }

    @Override
    @Transactional
    public Product addProduct(Product product) {
        Brand brand = findOrCreateBrand(product.getBrand());
        // Check for duplicates
        if (!productRepository.existsByNameAndBrand(product.getName(), brand)) {
            throw new IllegalArgumentException("Product with the same name and brand already exists.");
        }
        // Save and return the product
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
    public Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category with ID " + categoryId + " does not exist."));
    }

    @Override
    public void updateProductRating(Long productId) {
        // Get the average rating using a private method
        double average = calculateAverageRating(productId);

        // Find product by ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Продукт не найден"));

        // Set the average rating and save the product
        product.setAverageRating(average);
        productRepository.save(product);
    }

    private Double calculateAverageRating(Long productId) {
        // Get a list of ratings for the product
        List<Integer> ratings = productRepository.findRatingsByProductId(productId);

        if (ratings.isEmpty()) {
            return 0.0;
        }

        // Calculate the average value
        return ratings.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
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

    @Override
    public List<Brand> getAllBrands(String sortBy, String direction, int limit) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(0, limit, sort);
        return brandRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Brand> findBrandsByNameContainingIgnoreCase(String name, int limit, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(0, limit, sort);
        return brandRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    // Method for finding or creating a brand
    public Brand findOrCreateBrand(Brand brand) {
        // Bringing the brand name to a unified form: removing spaces and making it case-insensitive
        return brandRepository.findByNameIgnoreCase(brand.getName())
                .orElseGet(() -> brandRepository.save(brand));
    }
}