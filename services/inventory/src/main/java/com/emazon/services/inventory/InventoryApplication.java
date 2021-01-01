package com.emazon.services.inventory;

import com.emazon.services.inventory.dao.CategoryRepository;
import com.emazon.services.inventory.entity.Category;
import com.emazon.services.inventory.entity.Product;
import com.emazon.services.inventory.service.CategoryService;
import com.emazon.services.inventory.service.CategoryServiceImpl;
import com.emazon.services.inventory.service.ProductService;
import com.emazon.services.inventory.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.ArrayList;


@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)// authorise prepost annotation
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductService productService, CategoryService categoryService, CategoryRepository categoryRepository){
		return args -> {

			Category phones = new Category(null,"phones");
			categoryService.addCategory(phones);

			Product product1 = new Product(null,100,"iphone",phones);
			Product product2 = new Product(null,100,"samsung",phones);
			Product product3 = new Product(null,100,"huawei",phones);
			Product product4 = new Product(null,100,"xiaomi",phones);
			Product product5 = new Product(null,100,"wiko",phones);
			Product product6 = new Product(null,100,"sony",phones);
			Product product7 = new Product(null,100,"oneplus",phones);

			productService.addNewProduct(product1);
			productService.addNewProduct(product2);
			productService.addNewProduct(product3);
			productService.addNewProduct(product4);
			productService.addNewProduct(product5);
			productService.addNewProduct(product6);
			productService.addNewProduct(product7);

			phones.getProducts().add(product1);
			phones.getProducts().add(product2);
			phones.getProducts().add(product3);
			phones.getProducts().add(product4);
			phones.getProducts().add(product5);
			phones.getProducts().add(product6);
			phones.getProducts().add(product7);



//			Category accessoire = new Category(null,"accessoire",new ArrayList<>());
//			categoryService.addCategory(accessoire);
//
//			Product cable = new Product(null,50,"cable");
//			Product adaptateur = new Product(null,20,"adaptateur");
//
//			productService.addNewProduct(cable);
//			productService.addNewProduct(adaptateur);
//
//			accessoire.getProducts().add(cable);
//			accessoire.getProducts().add(adaptateur);


			//System.out.println(accessoire);
			System.out.println(phones);

		};
	}

}
