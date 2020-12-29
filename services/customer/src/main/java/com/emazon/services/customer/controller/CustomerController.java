package com.emazon.services.customer.controller;

import com.emazon.services.customer.entity.Customer;
import com.emazon.services.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/")
    public CollectionModel<EntityModel<Customer>> all() {
        return customerService.getCustomers();
    }

    @GetMapping("/get/{customerId}")
    public EntityModel<Customer> one(@PathVariable String customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping("/add")
    public EntityModel<Customer> addNewCustomer(@RequestBody Customer customer){
        return customerService.createNewCustomer(customer);
    }
}
