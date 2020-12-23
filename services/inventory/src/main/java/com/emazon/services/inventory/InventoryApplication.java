package com.emazon.services.inventory;

import com.emazon.services.inventory.dao.InventoryRepository;
import com.emazon.services.inventory.entity.Inventory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Bean
	CommandLineRunner start(InventoryRepository cr){
		return args -> {

			cr.save(new Inventory());

		};
	}

}
