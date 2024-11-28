package com.catbytes.reviews.mapper;

import com.catbytes.reviews.dto.BrandDTO;
import com.catbytes.reviews.entity.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public Brand toEntity(BrandDTO brandDTO) {
        if (brandDTO == null) {
            return null;
        }
        Brand brand = new Brand();
        brand.setId(brandDTO.getId());
        brand.setName(brandDTO.getName());
        return brand;
    }

    public BrandDTO toDTO(Brand brand) {
        if (brand == null) {
            return null;
        }
        return new BrandDTO(brand.getId(), brand.getName());
    }
}
