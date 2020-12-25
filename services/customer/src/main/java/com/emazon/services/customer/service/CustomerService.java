package com.emazon.services.customer.service;


import com.emazon.services.customer.dao.CustomerRepository;
import com.emazon.services.customer.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer getCustomerById(long id) {

        return customerRepository.findById(id).get();
    }

    public Customer getCustomerByFirstNameAndLastName(String firstName, String lastName) {
        return customerRepository.getCustomerByFirstNameAndLastName(firstName, lastName);
    }



    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


}
