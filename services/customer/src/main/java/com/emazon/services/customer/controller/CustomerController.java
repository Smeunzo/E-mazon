package com.emazon.services.customer.controller;

import com.emazon.services.customer.entity.Customer;
import com.emazon.services.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customer")
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;

    @GetMapping(path = "/{customerId}")
    public Customer customerInfo(@PathVariable("customerId" ) String customerId ){
        return customerService.getCustomerInfo(customerId);
    }
}
