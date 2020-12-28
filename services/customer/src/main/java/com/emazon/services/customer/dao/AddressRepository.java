package com.emazon.services.customer.dao;

import com.emazon.services.customer.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
