package com.emazon.services.inventory.controller;

import com.emazon.services.inventory.entity.Category;
import com.emazon.services.inventory.entity.Product;
import com.emazon.services.inventory.service.CategoryService;
import com.emazon.services.inventory.service.ProductService;
import com.emazon.services.inventory.util.CategoryProductData;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
public class InventoryController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping(path = "/inventory/products")
    public Collection<Product> getProducts() {
        return productService.loadProducts();
    }

    @GetMapping(path ="/inventory/category")
    public Collection<String> getCategories(){
        return categoryService.loadCategoriesNames();
    }

    @GetMapping(path = "/inventory/category/{name}")
    public Collection<Product> loadCategory(@PathVariable(value = "name") String categoryName){
        return categoryService.loadCategoryByName(categoryName).getProducts();
    }

    @PostMapping(path = "/inventory/addProduct")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Product addProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }

    @PostMapping(path = "/inventory/addCategory")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @PostMapping(path = "/inventory/addProductToCategory")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Category addProductToCategory(@RequestBody CategoryProductData categoryProductData){
        Product product = productService.loadProductByName(categoryProductData.getProductName());
        Category category = categoryService.loadCategoryByName(categoryProductData.getCategoryName());
        return categoryService.linkProductToCategory(category,product);
    }

    @GetMapping(path = "/inventory/product/{name}")
    public Product loadProduct(@PathVariable(value = "name") String productName){
        return productService.loadProductByName(productName);
    }

   
}

