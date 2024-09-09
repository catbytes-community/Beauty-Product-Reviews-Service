package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {
    Category findCategory(String name) throws CategoryNotFoundException;

    Category findCategory(Long id) throws CategoryNotFoundException;

    List<Category> getTreeCategories();

}
