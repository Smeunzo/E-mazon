package com.emazon.services.inventory.service;

import com.emazon.services.inventory.dao.CategoryRepository;
import com.emazon.services.inventory.entity.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor

public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository ;

    public Category addCategory(Category category){
        System.out.println(category);
       return  categoryRepository.save(category);
    }

    public Category loadCategoryByName(String categoryName){
        System.out.println(categoryName);
        return categoryRepository.findCategoryByName(categoryName);
    }
}
