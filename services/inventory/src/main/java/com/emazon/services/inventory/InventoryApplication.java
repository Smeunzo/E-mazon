package com.emazon.services.inventory;

import com.emazon.services.inventory.entity.Category;
import com.emazon.services.inventory.entity.Product;
import com.emazon.services.inventory.service.CategoryService;
import com.emazon.services.inventory.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)// authorise prepost annotation
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductService productService, CategoryService categoryService){
		return args -> {

			Category phones = new Category();
			phones.setName("phones");
			categoryService.addCategory(phones);

			Product product1 = new Product(null,100,"iphone",100,"description");
			Product product2 = new Product(null,100,"samsung",100,"description");
			Product product3 = new Product(null,100,"huawei",100,"description");
			Product product4 = new Product(null,100,"xiaomi",100,"description");
			Product product5 = new Product(null,100,"wiko",100,"description");
			Product product6 = new Product(null,100,"sony",100,"description");
			Product product7 = new Product(null,100,"oneplus",100,"description");

			productService.addNewProduct(product1);
			productService.addNewProduct(product2);
			productService.addNewProduct(product3);
			productService.addNewProduct(product4);
			productService.addNewProduct(product5);
			productService.addNewProduct(product6);
			productService.addNewProduct(product7);

			categoryService.linkProducts(phones,product1,product2,product3,product4,product5,product6,product7);

		};
	}

}
