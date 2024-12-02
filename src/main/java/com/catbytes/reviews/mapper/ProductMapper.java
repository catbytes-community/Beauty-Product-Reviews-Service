package com.catbytes.reviews.mapper;

import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getCategory().getId(),
                product.getAverageRating()
        );
    }

    public Product toEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category with ID " + productDTO.getCategoryId() + " does not exist."));

        return new Product(
                productDTO.getName(),
                productDTO.getBrand(),
                category,
                productDTO.getAverageRating()
        );
    }
}
