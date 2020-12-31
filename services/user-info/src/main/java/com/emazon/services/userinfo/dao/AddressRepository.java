package com.emazon.services.userinfo.dao;

import com.emazon.services.userinfo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
