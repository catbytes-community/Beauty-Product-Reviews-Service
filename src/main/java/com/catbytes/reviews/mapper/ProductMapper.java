package com.catbytes.reviews.mapper;

import com.catbytes.reviews.dto.ProductDTO;
import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
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


}