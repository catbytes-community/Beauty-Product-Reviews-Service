package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {
    Category findCategory(String name) throws CategoryNotFoundException;

    void saveCategory(Category category);

    void saveAllCategories(List<Category> category);

    void editCategory(Category category);

    List<Category> findAllCategories();

    List<Category> createTreeCategoriesByParent(final List<Category> categories, Integer parentId);

    Category findCategory(int id) throws CategoryNotFoundException;

    void deleteCategory(String name);

    void deleteCategory(int id);

}
