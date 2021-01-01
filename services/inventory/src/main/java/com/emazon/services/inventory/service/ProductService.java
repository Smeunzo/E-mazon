package com.emazon.services.inventory.service;

import com.emazon.services.inventory.entity.Product;

import java.util.Collection;

public interface ProductService {
    Collection<Product> loadProducts();
    Product addNewProduct(Product product);
    Product loadProductByName(String productName);
}
