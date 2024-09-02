package com.catbytes.reviews.controller.rest;

import com.catbytes.reviews.dto.CategoryDTO;
import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.mapper.CategoryMapper;
import com.catbytes.reviews.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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
    public Set<CategoryDTO> getCategories() {
        return categoryService.createTreeCategoriesByParent((categoryService.findAllCategories()), null).stream()
                .map(categoryMapper::convertToDTO)
                .collect(Collectors.toSet());
    }

    @PostMapping("/categories")
    public String saveAllCategories(@RequestBody List<CategoryDTO> categoriesDTO) {
        List<Category> savedCategories = new ArrayList<>();
        categoriesDTO.forEach(dto -> categoryService.saveCategory(categoryMapper.convertToEntity(dto)));
        return "Categories saved successfully";
    }

}
