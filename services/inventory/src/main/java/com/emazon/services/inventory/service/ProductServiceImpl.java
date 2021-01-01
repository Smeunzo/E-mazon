package com.emazon.services.inventory.service;


import com.emazon.services.inventory.dao.ProductRepository;
import com.emazon.services.inventory.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository ;

    public Collection<Product> loadProducts() {
        return productRepository.findAll();
    }

    public Product addNewProduct(Product product){
        System.out.println(product);
        return productRepository.save(product);
    }

    public Product loadProductByName(String productName){
        return productRepository.findProductByName(productName);
    }
}
