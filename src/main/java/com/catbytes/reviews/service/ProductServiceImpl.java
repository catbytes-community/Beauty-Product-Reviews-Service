package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.entity.Brand;
import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.repository.BrandRepository;
import com.catbytes.reviews.mapper.ProductMapper;
import com.catbytes.reviews.repository.CategoryRepository;
import com.catbytes.reviews.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
                              ProductMapper productMapper, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;

    }

    @Override
    @Transactional
    public Product addProduct(ProductDTO productDTO) {
        // Find the category
        Category category = findCategoryById(productDTO.getCategoryId());

        // Convert BrandDTO to Brand
        Brand brand = findOrCreateBrand(new Brand(productDTO.getBrand().getName()));

        // Convert the DTO to an entity, passing in the category and brand
        Product product = productMapper.toEntity(productDTO, category, brand);

        // Check for duplicates
        Optional<Product> existingProduct = productRepository.findByNameAndBrand(product.getName(), brand);
        if (existingProduct.isPresent()) {
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
    public Double calculateAverageRating(Long productId) {
        //TODO: implement after [#11] - Review Entity and Review Posting API
        return null;
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