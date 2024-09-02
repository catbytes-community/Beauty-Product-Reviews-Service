package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.exception.CategoryNotFoundException;
import com.catbytes.reviews.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl (CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findCategory(String name) throws CategoryNotFoundException {
        return categoryRepository.findCategoryByName(name).orElseThrow(() -> new CategoryNotFoundException("Category with name " + name + " not found"));
    }

    @Override
    public Category findCategory(int id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void saveAllCategories(List<Category> category) {
        categoryRepository.saveAll(category);
    }

    @Override
    public void editCategory(Category editCategory) {
        categoryRepository.save(editCategory);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
/*
    a smart method to return the category tree, I haven't figured it out yet
    https://stackoverflow.com/questions/41593828/representing-tree-data-structure-in-rest-as-url-sequence
    */
    @Override
    public List<Category> createTreeCategoriesByParent(final List<Category> categories, Integer parentId) {
        List<Category> siblings = new ArrayList<>();
        categories.forEach(category -> {
            if (Objects.equals(category.getParent() == null ? null : category.getParent().getId(), parentId)) {
                List<Category> subCategories = createTreeCategoriesByParent(categories, category.getId());
                subCategories.sort(Category::compareTo);
                category.setSubCategories(subCategories);
                siblings.add(category);
            }
        });
        return siblings;
    }

    @Override
    public void deleteCategory(String name) {
        Category category = categoryRepository.findCategoryByName(name).orElseThrow(() -> new CategoryNotFoundException("Category with name " + name + " not found"));
        categoryRepository.delete(category);
    }

    @Override
    public void deleteCategory(int id) {
        if (id > 0) {
            categoryRepository.deleteById(id);
        } else {
            throw new CategoryNotFoundException("That id is not valid");
        }
    }

}
