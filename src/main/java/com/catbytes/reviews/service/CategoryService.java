package com.catbytes.reviews.service;

import com.catbytes.reviews.entity.Category;
import com.catbytes.reviews.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {

    List<Category> getTreeCategories();

}
