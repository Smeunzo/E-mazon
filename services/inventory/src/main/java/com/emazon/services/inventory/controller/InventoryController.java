package com.emazon.services.inventory.controller;

import com.emazon.services.inventory.entity.Category;
import com.emazon.services.inventory.entity.Product;
import com.emazon.services.inventory.service.CategoryService;
import com.emazon.services.inventory.service.CategoryServiceImpl;
import com.emazon.services.inventory.service.ProductService;
import com.emazon.services.inventory.service.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@AllArgsConstructor
public class InventoryController {

    private final ProductService productServiceImpl;
    private final CategoryService categoryServiceImpl;

    @GetMapping(path = "/inventory/products")
    @PostAuthorize("hasAuthority('USER')")
    public Collection<Product> getProducts() {
        return productServiceImpl.loadProducts();
    }

    @PostMapping(path = "inventory/addProduct")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Product addProduct(@RequestBody Product product){
        return productServiceImpl.addNewProduct(product);
    }

    @PostMapping(path = "/inventory/addCategory")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Category addCategory(@RequestBody Category category){
        return categoryServiceImpl.addCategory(category);
    }

    @GetMapping(path = "/inventory/category/{categoryName}")
    @PostAuthorize("hasAuthority('USER')")
    public Collection<Product> loadCategory(String categoryName){
        return categoryServiceImpl.loadCategoryByName(categoryName).getProducts();
    }






}
