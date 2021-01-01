package com.emazon.services.inventory.service;

import com.emazon.services.inventory.dao.CategoryRepository;
import com.emazon.services.inventory.entity.Category;
import com.emazon.services.inventory.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor

public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository ;

    @Override
    public Category addCategory(Category category){
        System.out.println(category);
       return  categoryRepository.save(category);
    }

    @Override
    public Category loadCategoryByName(String categoryName){
        System.out.println(categoryName);
        return categoryRepository.findCategoryByName(categoryName);
    }

    @Override
    @Transactional
    public Category addProduct(Category c, Product p){
        Category category = categoryRepository.findCategoryByName(c.getName());

        category.getProducts().add(p);
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category addProducts(Category c, Product... p){
        Category category = categoryRepository.findCategoryByName(c.getName());

        category.getProducts().addAll(Arrays.stream(p).collect(Collectors.toList()));
        return categoryRepository.save(category);
    }

    public Collection<Category> loadCategories(){
        return categoryRepository.findAll();
    }
}
