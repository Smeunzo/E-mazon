package com.emazon.services.inventory;

import com.emazon.services.inventory.dao.InventoryRepository;
import com.emazon.services.inventory.dao.ProductRepository;
import com.emazon.services.inventory.entity.Inventory;
import com.emazon.services.inventory.entity.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Bean
	CommandLineRunner start(InventoryRepository cr, ProductRepository pr){
		return args -> {

			Inventory inventory = new Inventory(null,new ArrayList<>());
			Product product1 = new Product(null,100,"iphone",inventory);
			Product product2 = new Product(null,100,"samsung",inventory);
			Product product3 = new Product(null,100,"huawei",inventory);
			Product product4 = new Product(null,100,"xiaomi",inventory);
			Product product5 = new Product(null,100,"wiko",inventory);
			Product product6 = new Product(null,100,"sony",inventory);
			Product product7 = new Product(null,100,"oneplus",inventory);
			inventory.getProducts().add(product1);
			inventory.getProducts().add(product2);
			inventory.getProducts().add(product3);
			inventory.getProducts().add(product4);
			inventory.getProducts().add(product5);
			inventory.getProducts().add(product6);
			inventory.getProducts().add(product7);
			cr.save(inventory);
			pr.saveAll(inventory.getProducts());

		};
	}

}
