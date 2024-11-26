package com.catbytes.reviews.mapper;

import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.dto.BrandDTO;
import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.entity.Brand;
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
        // Transforming the brand into BrandDTO
        BrandDTO brandDTO = new BrandDTO(product.getBrand().getId(), product.getBrand().getName());

        return new ProductDTO(
                product.getId(),
                product.getName(),
                brandDTO,
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

        // Create a product entity
        return new Product(
                productDTO.getName(),
                new Brand(productDTO.getBrand().getName()),  // Create a brand from BrandDTO
                category,
                productDTO.getAverageRating()
        );
    }
}
