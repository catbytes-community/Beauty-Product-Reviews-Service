package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.exception.CategoryNotFoundException;
import com.catbytes.reviews.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getTreeCategories() {

        List<Category> rootCategories = categoryRepository.findRootCategories();
        return createTreeCategories(rootCategories);

    }

    private List<Category> createTreeCategories(List<Category> categories) {
        categories.forEach(category -> {
            List<Category> subСategories = categoryRepository.findByParentId(category.getId());
            category.setSubCategories(createTreeCategories(subСategories));
        });
        return categories;
    }
}
