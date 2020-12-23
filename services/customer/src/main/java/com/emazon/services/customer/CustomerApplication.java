package com.emazon.services.customer;

import com.emazon.services.customer.dao.CustomerRepository;
import com.emazon.services.customer.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository cr){
		return args -> {
			Customer c = new Customer();
			c.setFirstName("Abdelhakim");
			c.setLastName("BELHACHEMI");

			cr.save(c);
		};
	}
}
