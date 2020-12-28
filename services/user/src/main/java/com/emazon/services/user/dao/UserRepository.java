package com.emazon.services.user.dao;


import com.emazon.services.user.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserCredentials, Long> {
    UserCredentials findUserCredentialsByUsername(String username);
}
