package com.emazon.services.inventory.service;

import com.emazon.services.inventory.entity.Category;

import java.util.Collection;

public interface CategoryService {

    Category addCategory(Category category);
    Category loadCategoryByName(String categoryName);
    Collection<Category> loadCategories();
}
