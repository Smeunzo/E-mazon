package com.emazon.services.customer.config;

import com.emazon.services.customer.dao.AddressRepository;
import com.emazon.services.customer.dao.CustomerRepository;
import com.emazon.services.customer.entity.Address;
import com.emazon.services.customer.entity.Customer;
import com.emazon.services.customer.service.CustomerService;
import com.emazon.services.customer.service.CustomerServiceImpl;
import com.emazon.services.customer.utils.UUIDGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class ServiceConfig {

    @Bean
    CommandLineRunner loadDatabase(CustomerRepository customerRepository, AddressRepository addressRepository){
        return args -> {
            Address address = new Address();

            address.setStreetNumber(41);
            address.setStreetName("Rue du chien la");

            address.setZipCode("13013");
            address.setCity("Marseille");
            address.setCountry("France");

            addressRepository.save(address);

            Customer customer = new Customer();

            customer.setFirstName("Ben");
            customer.setLastName("Beckman");
            customer.setAddress(address);
            customer.setEmail("email@example.fr");
            customer.setBirthdate(LocalDate.of(1990, Month.APRIL,2));

            customer.setCustomerId(UUIDGenerator.generate());

            customerRepository.save(customer);
        };
    }
}
