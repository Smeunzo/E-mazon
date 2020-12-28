package com.emazon.services.customer.dao;

import com.emazon.services.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByCustomerId(String customerId);
}
