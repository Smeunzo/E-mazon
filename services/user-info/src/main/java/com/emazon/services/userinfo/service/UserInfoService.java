package com.emazon.services.userinfo.service;

import com.emazon.services.userinfo.entity.UserInfo;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public interface UserInfoService {

    CollectionModel<EntityModel<UserInfo>> getCustomers();

    EntityModel<UserInfo> getCustomerById(String customerId);

    EntityModel<UserInfo> createNewCustomer(UserInfo userInfo);

    boolean updateCustomer(String customerId, UserInfo userInfo);

    boolean removeCustomer (String customerId);
}
