package com.catbytes.reviews.mapper;

import com.catbytes.reviews.dto.CategoryDTO;
import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.repository.CategoryRepository;
import com.catbytes.reviews.service.CategoryService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    private final CategoryService categoryService;

    public CategoryMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public CategoryDTO convertToDTO(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        // Set parentId only if parent is not null
        if (category.getParent() != null) {
            categoryDTO.setParentId(category.getParent().getId());
        }

//        List<CategoryDTO> subCategoriesDTOList = new ArrayList<>();
//        for (Category subCategory : category.getSubCategories()) {
//            subCategoriesDTOList.add(convertToDTO(subCategory));
//        }
//        categoryDTO.setSubCategories(subCategoriesDTOList);
        List<CategoryDTO> subCategoriesDTOList = category.getSubCategories().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        categoryDTO.setSubCategories(subCategoriesDTOList);

        return categoryDTO;

    }

    public Category convertToEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        Category category = new Category();

        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        if (categoryDTO.getParentId() == null) {
            category.setParent(null);
        } else {
            category.setParent(categoryService.findCategory(categoryDTO.getParentId()));
        }
        if (categoryDTO.getSubCategories() == null) {
            category.setSubCategories(new ArrayList<>());
        } else {
            List<Category> subCategories = categoryDTO.getSubCategories().stream()
                    .map(this::convertToEntity)
                    .collect(Collectors.toList());
            category.setSubCategories(subCategories);
        }

        return category;
    }
}
