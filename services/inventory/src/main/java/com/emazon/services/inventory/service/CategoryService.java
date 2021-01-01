package com.emazon.services.inventory.service;

import com.emazon.services.inventory.entity.Category;
import com.emazon.services.inventory.entity.Product;

import java.util.Collection;

public interface CategoryService {

    Category addCategory(Category category);

    Category loadCategoryByName(String categoryName);

    Category linkProduct(Category c, Product p);

    Category linkProducts(Category c, Product... p);

    Collection<Category> loadCategories();
}
