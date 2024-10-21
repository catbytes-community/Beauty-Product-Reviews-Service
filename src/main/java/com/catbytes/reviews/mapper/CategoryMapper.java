package com.catbytes.reviews.mapper;

import com.catbytes.reviews.dto.CategoryDTO;
import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.service.CategoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryMapper.class);

    public CategoryDTO convertToDTO(Category category) {
        LOG.info("Starting to convertToDTO");
        if (category == null) {
            LOG.warn("category is null");
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        if (category.getParent() != null) {
            categoryDTO.setParentId(category.getParent().getId());
        }

        if (category.getSubCategories() != null) {
            List<CategoryDTO> subCategoriesDTOList = category.getSubCategories().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            categoryDTO.setSubCategories(subCategoriesDTOList);
        }
        LOG.debug("convert to DTO successful");
        return categoryDTO;
    }
}
