package com.catbytes.reviews.controller.rest;

import com.catbytes.reviews.dto.CategoryDTO;
import com.catbytes.reviews.mapper.CategoryMapper;
import com.catbytes.reviews.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class CategoryController {

    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    public CategoryController(CategoryMapper categoryMapper, CategoryService categoryService) {
        this.categoryMapper = categoryMapper;
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public Set<CategoryDTO> getTreeOfCategory() {
        return categoryService.getTreeCategories(null).stream()
                .map(categoryMapper::convertToDTO)
                .collect(Collectors.toSet());
    }
    @GetMapping("/categories/{paren_id}")
    public Set<CategoryDTO> getTreeOfCategory(@PathVariable Long paren_id) {
        return categoryService.getTreeCategories(paren_id).stream()
                .map(categoryMapper::convertToDTO)
                .collect(Collectors.toSet());
    }


}
