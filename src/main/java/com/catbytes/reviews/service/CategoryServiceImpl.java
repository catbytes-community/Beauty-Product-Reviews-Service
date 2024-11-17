package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;


    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        } else {
            throw new RuntimeException("Category not found for id: " + id);
        }
    }

    public List<Category> getTreeCategories() {
        LOG.info("Starting to get tree categories");
        List<Category> rootCategories = categoryRepository.findRootCategories();
        if (rootCategories.isEmpty()) {
            LOG.warn("No root categories found");
        } else {
            LOG.debug("Found {} root categories", rootCategories.size());
        }
        LOG.debug("Tree categories successfully created");
        return createTreeCategories(rootCategories);
    }

    private List<Category> createTreeCategories(List<Category> categories) {
        LOG.info("Creating tree structure for {} categories", categories.size());
        categories.forEach(category -> {
            LOG.debug("Processing category with id: {}", category.getId());
            List<Category> subCategories = categoryRepository.findByParentId(category.getId());
            if (subCategories.isEmpty()) {
                LOG.debug("No subcategories found for category id: {}", category.getId()); // Логирование отсутствия подкатегорий
            } else {
                LOG.debug("Found {} subcategories for category id: {}", subCategories.size(), category.getId()); // Логирование количества подкатегорий
            }
            category.setSubCategories(createTreeCategories(subCategories));
        });
        LOG.debug("Tree structure created successfully for {} categories", categories.size());
        return categories;
    }
}