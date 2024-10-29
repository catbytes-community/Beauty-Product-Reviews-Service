package com.catbytes.reviews.controller.rest;

import com.catbytes.reviews.dto.CategoryDTO;
import com.catbytes.reviews.mapper.CategoryMapper;
import com.catbytes.reviews.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product/category")
@Tag(name = "Product Category")
public class ProductCategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductCategoryController.class);

    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    public ProductCategoryController(CategoryMapper categoryMapper, CategoryService categoryService) {
        this.categoryMapper = categoryMapper;
        this.categoryService = categoryService;
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get categories tree",
            description = "Find and return list of all categories and their sub-categories as tree")
    public Set<CategoryDTO> getTreeOfCategory() {
        LOG.info("GET /product/category request received");
        return categoryService.getTreeCategories().stream()
                .map(categoryMapper::convertToDTO)
                .collect(Collectors.toSet());
    }
}
