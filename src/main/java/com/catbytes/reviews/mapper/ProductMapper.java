package com.catbytes.reviews.mapper;

import com.catbytes.reviews.dto.BrandDTO;
import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.entity.Brand;
import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final BrandMapper brandMapper;

    public ProductMapper(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
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

    public Product toEntity(ProductDTO productDTO, Category category) {
        Brand brand = brandMapper.toEntity(productDTO.getBrand());
        return new Product(
                productDTO.getName(),
                brand,
                category
        );
    }

}

