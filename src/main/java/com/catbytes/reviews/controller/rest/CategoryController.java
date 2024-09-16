package com.catbytes.reviews.controller.rest;

import com.catbytes.reviews.dto.CategoryDTO;
import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.mapper.CategoryMapper;
import com.catbytes.reviews.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class CategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    public CategoryController(CategoryMapper categoryMapper, CategoryService categoryService) {
        this.categoryMapper = categoryMapper;
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public Set<CategoryDTO> getTreeOfCategory() {
        LOG.info("GET /product/categories request received");
        return categoryService.getTreeCategories().stream()
                .map(categoryMapper::convertToDTO)
                .collect(Collectors.toSet());
    }
}
