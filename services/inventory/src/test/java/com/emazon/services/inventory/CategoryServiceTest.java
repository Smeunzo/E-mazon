package com.emazon.services.inventory;
import com.emazon.services.inventory.dao.CategoryRepository;
import com.emazon.services.inventory.entity.Category;
import com.emazon.services.inventory.entity.Product;
import com.emazon.services.inventory.exception.AlreadyExistException;
import com.emazon.services.inventory.service.CategoryService;
import com.emazon.services.inventory.service.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CategoryServiceTest {

    private static Category category1 ;
    private static Category category2 ;
    private static Category newCategory ;
    private static Category categoryAlreadyAddedInRepository ;

    private static Product product1 ;
    private static Product product2 ;



    @BeforeAll
    static void createCategoriesAndProducts(){

        category1 = new Category(null,"phones",new ArrayList<>(),null); //Description can be null
        category2 = new Category(null,"accessory",new ArrayList<>(),null);
        newCategory = new Category(null,"newCategory",new ArrayList<>(),"new category");
        categoryAlreadyAddedInRepository = new Category(null,"categoryAlreadyAddedInRepository",new ArrayList<>(),null);

        product1 = new Product(2000,"iphone",20,"telephone chere");
        product2 = new Product(1000,"samsung",50,"meilleur telephone");

    }

    private static final CategoryRepository categoryRepository = mock(CategoryRepository.class);

    @BeforeEach
    void mockRepositories(){
        when(categoryRepository.save(newCategory))
                .thenReturn(newCategory);

        when(categoryRepository.findCategoryByName(category1.getName()))
                .thenReturn(category1);
        when(categoryRepository.findCategoryByName(category2.getName()))
                .thenReturn(category2);

        when(categoryRepository.existsCategoryByName(category1.getName()))
                .thenReturn(true);
        when(categoryRepository.existsCategoryByName(category2.getName()))
                .thenReturn(true);

        when(categoryRepository.findAll())
                .thenReturn(List.of(category1,category2));
    }

    @BeforeAll
    static void mockThrows(){
        when(categoryRepository.save(categoryAlreadyAddedInRepository))
                .thenThrow(AlreadyExistException.class);
    }

    CategoryService categoryService = new CategoryServiceImpl(categoryRepository);

    @Test
    void shouldReturnCategories(){
        Collection<Category> categoryList = categoryService.loadCategories();
        assertEquals(List.of(category1,category2),categoryList);
    }

    @Test
    void shouldReturnProductByName(){
        Category category = categoryService.loadCategoryByName(category1.getName());
        assertEquals(category,category1);

        category = categoryService.loadCategoryByName(category2.getName());
        assertEquals(category,category2);

    }

    @Test
    void shouldLinkProductToCategory(){
        assertFalse(category1.getProducts().contains(product1));

        categoryService.linkProductToCategory(category1,product1);
        assertTrue(category1.getProducts().contains(product1));
    }

    @Test
    void shouldLinkProductsToCategory(){
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        assertFalse(category2.getProducts().containsAll(products));

        categoryService.linkProductsToCategory(category2,product1,product2);
        assertTrue(category2.getProducts().containsAll(products));
    }

    @Test
    void shouldCreateCategory(){
        Category category = categoryService.addCategory(newCategory);
        assertEquals(category,newCategory);
    }

    @Test
    void shouldThrowAlreadyExistsException(){
        assertThrows(AlreadyExistException.class,()-> categoryService.addCategory(categoryAlreadyAddedInRepository));
    }
}
