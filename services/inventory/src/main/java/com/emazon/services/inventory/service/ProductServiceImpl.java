package com.emazon.services.inventory.service;

import com.emazon.services.inventory.dao.ProductRepository;
import com.emazon.services.inventory.entity.Product;
import com.emazon.services.inventory.entity.Rate;
import com.emazon.services.inventory.exception.AlreadyExistException;
import com.emazon.services.inventory.util.UtilService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository ;

    public Collection<Product> loadProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product addNewProduct(Product product){
        UtilService.validate(product);
        if(productRepository.existsProductByName(product.getName())){
            throw new AlreadyExistException(product.getName());
        }

        return productRepository.save(product);
    }

    public Product loadProductByName(String productName){
        return productRepository.findProductByName(productName);
    }

    @Override
    @Transactional
    public Product linkRateToProduct(Product p, Rate... r) {
        Product product = loadProductByName(p.getName());
        product.getRates().addAll(Arrays.stream(r).collect(Collectors.toList()));
        return productRepository.save(product);
    }
}
