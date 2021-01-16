package com.emazon.services.inventory;

import com.emazon.services.inventory.dao.ProductRepository;
import com.emazon.services.inventory.entity.Product;
import com.emazon.services.inventory.exception.AlreadyExistException;
import com.emazon.services.inventory.service.interfaces.ProductService;
import com.emazon.services.inventory.service.implementations.ProductServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ProductServiceTest {

    private static Product product1 ;
    private static Product product2 ;
    private static Product product3 ;
    private static Product newProduct;
    private static Product productAlreadyAddedInRepository ;

    @BeforeAll
    static void createRoles(){

        product1 = new Product(2000,"iphone",20,"telephone chere");
        product2 = new Product(1000,"samsung",50,"meilleur telephone");
        product3 = new Product(500,"huawei",100,null); //Description can be null
        newProduct = new Product(40,"wiko",5,null);
        productAlreadyAddedInRepository = new Product(5000,"sony",500,null);

    }

    private static final ProductRepository productRepository = mock(ProductRepository.class);

    @BeforeEach
    void mockRepositories(){
        when(productRepository.save(newProduct))
                .thenReturn(newProduct);

        when(productRepository.findProductByName(product1.getName()))
                .thenReturn(product1);
        when(productRepository.findProductByName(product2.getName()))
                .thenReturn(product2);
        when(productRepository.findProductByName(product3.getName()))
                .thenReturn(product3);

        when(productRepository.existsProductByName(product1.getName()))
                .thenReturn(true);
        when(productRepository.existsProductByName(product2.getName()))
                .thenReturn(true);
        when(productRepository.existsProductByName(product3.getName()))
                .thenReturn(true);

        when(productRepository.findAll())
                .thenReturn(List.of(product1,product2,product3));

    }

    @BeforeAll
    static void mockThrows(){
        when(productRepository.save(productAlreadyAddedInRepository))
                .thenThrow(AlreadyExistException.class);
    }

    ProductService productService = new ProductServiceImpl(productRepository);

    @Test
    void shouldReturnAllProducts(){
        Collection<Product> productList = productService.loadProducts();
        assertEquals(List.of(product1,product2,product3),productList);
    }

    @Test
    void shouldReturnProductByName(){
        Product product = productService.loadProductByName(product1.getName());
        assertEquals(product,product1);

        product = productService.loadProductByName(product2.getName());
        assertEquals(product,product2);

        product = productService.loadProductByName(product3.getName());
        assertEquals(product,product3);

    }

    @Test
    void shouldCreateNewProduct(){
        Product product = productService.addNewProduct(newProduct);
        assertEquals(product,newProduct);
    }

    @Test
    void shouldThrowAlreadyExistsException(){
        assertThrows(AlreadyExistException.class,()-> productService.addNewProduct(productAlreadyAddedInRepository));
    }

}
