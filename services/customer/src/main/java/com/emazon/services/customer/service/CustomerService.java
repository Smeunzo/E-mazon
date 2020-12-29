package com.emazon.services.customer.service;

import com.emazon.services.customer.dao.CustomerRepository;
import com.emazon.services.customer.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public void createCustomer(){

    }

}
