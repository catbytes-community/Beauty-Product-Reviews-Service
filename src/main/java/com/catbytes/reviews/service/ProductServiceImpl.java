package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.entity.Brand;
import com.catbytes.reviews.repository.BrandRepository;
import com.catbytes.reviews.mapper.ProductMapper;
import com.catbytes.reviews.repository.CategoryRepository;
import com.catbytes.reviews.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
                              ProductMapper productMapper, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public Product addProduct(Product product) {
        // Check if a brand by name exists
        Brand brand = brandRepository.findByName(product.getBrand().getName())
                .orElseGet(() -> brandRepository.save(new Brand(product.getBrand().getName())));

        product.setBrand(brand);

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
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand addBrand(String brandName) {
        return brandRepository.findByName(brandName)
                .orElseGet(() -> brandRepository.save(new Brand(brandName)));
    }
}
