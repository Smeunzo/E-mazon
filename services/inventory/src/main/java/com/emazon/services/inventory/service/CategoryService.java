package com.emazon.services.inventory.service;

import com.emazon.services.inventory.entity.Category;
import com.emazon.services.inventory.entity.Product;

import java.util.Collection;

public interface CategoryService {

    Category addCategory(Category category);

    Category loadCategoryByName(String categoryName);

    Category addProduct(Category c, Product p);

    Category addProducts(Category c, Product... p);

    Collection<Category> loadCategories();
}
