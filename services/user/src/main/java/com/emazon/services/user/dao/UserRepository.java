package com.emazon.services.user.dao;

import com.emazon.services.customer.entity.Customer;
import com.emazon.services.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUserByEmail(String email);
    User getUserByFirstNameAndLastName(String firstName, String lastName);
    User findById(long id);
}
