package com.catbytes.reviews.mapper;

import com.catbytes.reviews.dto.CategoryDTO;
import com.catbytes.reviews.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDTO convertToDTO(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        if (category.getParent() != null) {
            categoryDTO.setParentId(category.getParent().getId());
        }

        List<CategoryDTO> subCategoriesDTOList = category.getSubCategories().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        categoryDTO.setSubCategories(subCategoriesDTOList);

        return categoryDTO;

    }
}
