package com.emazon.services.userinfo.service;

import com.emazon.services.userinfo.entity.UserInfo;

import java.util.List;

public interface UserInfoService {

    List<UserInfo> getCustomers();

    UserInfo getCustomerById(String customerId);

    UserInfo createNewCustomer(UserInfo userInfo);

    boolean updateCustomer(String customerId, UserInfo userInfo);

    boolean removeCustomer (String customerId);
}
