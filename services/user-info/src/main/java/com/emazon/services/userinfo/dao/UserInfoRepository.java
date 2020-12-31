package com.emazon.services.userinfo.dao;

import com.emazon.services.userinfo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByCustomerId(String customerId);
    boolean existsByEmail(String email);
}
