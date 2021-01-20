package com.emazon.services.inventory.service;

import com.emazon.services.inventory.dao.CategoryRepository;
import com.emazon.services.inventory.entity.Category;
import com.emazon.services.inventory.entity.Product;
import com.emazon.services.inventory.exception.AlreadyExistException;
import com.emazon.services.inventory.util.UtilService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Validated
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository ;

    @Override
    @Transactional
    public Category addCategory(Category category){
        UtilService.validate(category);
        if(categoryRepository.existsCategoryByName(category.getName())){
            throw new AlreadyExistException(category.getName());
        }

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
    public Category linkProductToCategory(Category c, Product p){
        Category category = categoryRepository.findCategoryByName(c.getName());

        category.getProducts().add(p);
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category linkProductsToCategory(Category c, Product... p){
        Category category = categoryRepository.findCategoryByName(c.getName());
        category.getProducts().addAll(Arrays.stream(p).collect(Collectors.toList()));
        return categoryRepository.save(category);
    }

    public Collection<Category> loadCategories(){
        return categoryRepository.findAll();
    }

    @Override
    public Collection<String> loadCategoriesNames() {
        Collection<Category> categories = loadCategories();
        Collection<String> categoriesNames = new ArrayList<>();
        categories.forEach((category)->{categoriesNames.add(category.getName());});
        return categoriesNames ;
    }
}
