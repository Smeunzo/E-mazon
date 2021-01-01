package com.emazon.services.inventory.service;

import com.emazon.services.inventory.entity.Category;

public interface CategoryService {

    Category addCategory(Category category);
    Category loadCategoryByName(String categoryName);
}
