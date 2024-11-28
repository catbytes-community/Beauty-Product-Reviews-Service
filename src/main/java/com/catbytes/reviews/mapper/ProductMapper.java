package com.catbytes.reviews.mapper;

import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.entity.Brand;
import com.catbytes.reviews.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final CategoryRepository categoryRepository;
    private final BrandMapper brandMapper;

    @Autowired
    public ProductMapper(CategoryRepository categoryRepository, BrandMapper brandMapper) {
        this.categoryRepository = categoryRepository;
        this.brandMapper = brandMapper;
    }

    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductDTO(
                product.getId(),
                product.getName(),
                brandMapper.toDTO(product.getBrand()), // Use BrandMapper for BrandDTO
                product.getCategory().getId(),
                product.getAverageRating()
        );
    }

    public Product toEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Category with ID " + productDTO.getCategoryId() + " does not exist."
                ));

        // Use BrandMapper to convert
        Brand brand = brandMapper.toEntity(productDTO.getBrand());

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setBrand(brand);
        product.setCategory(category);
        product.setAverageRating(productDTO.getAverageRating());

        return product;
    }

}
