package com.emazon.services.customer.controller;


import com.emazon.services.customer.entity.Customer;
import com.emazon.services.customer.service.CustomerService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping("/customers/getCustomerByName")
    public Customer getCustomerByFirstNameAndLastName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        System.out.println(firstName);
        return customerService.getCustomerByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/customers/getCustomerById")
    public Customer getCustomerByFirstNameAndLastName(@RequestParam("id") String id) {
        return customerService.getCustomerById(Long.parseLong(id));
    }


    @PostMapping("/customers/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        return customerService.addCustomer(customer);

    }

}
