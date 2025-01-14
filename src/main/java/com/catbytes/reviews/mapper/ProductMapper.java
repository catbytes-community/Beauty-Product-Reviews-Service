package com.catbytes.reviews.mapper;

import com.catbytes.reviews.controller.rest.ProductCategoryController;
import com.catbytes.reviews.dto.BrandDTO;
import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.entity.Brand;
import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.entity.Product;
import com.catbytes.reviews.repository.CategoryRepository;
import com.catbytes.reviews.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final BrandMapper brandMapper;
    private final CategoryRepository categoryRepository;


    public ProductMapper(BrandMapper brandMapper, CategoryRepository categoryRepository) {
        this.brandMapper = brandMapper;
        this.categoryRepository = categoryRepository;
    }

    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        BrandDTO brandDTO = brandMapper.toDTO(product.getBrand());

        return new ProductDTO(
                product.getId(),
                product.getName(),
                brandDTO,
                product.getCategory().getId(),
                product.getAverageRating()
        );
    }

    public Product toEntity(ProductDTO productDTO) {
        Brand brand = brandMapper.toEntity(productDTO.getBrand());
        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        return new Product(
                productDTO.getName(),
                brand,
                category
        );
    }

}

