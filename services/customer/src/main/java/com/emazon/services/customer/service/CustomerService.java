package com.emazon.services.customer.service;

import com.emazon.services.customer.entity.Customer;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface CustomerService {

    CollectionModel<EntityModel<Customer>> getCustomers();

    EntityModel<Customer> getCustomerById(String customerId);

    EntityModel<Customer> createNewCustomer(Customer customer);

    boolean updateCustomer(String customerId, Customer customer);

    boolean removeCustomer (String customerId);
}
