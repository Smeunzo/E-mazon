package com.emazon.services.inventory.dao;

import com.emazon.services.inventory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category findCategoryByName(String name);
    boolean existsCategoryByName(String name );
}
