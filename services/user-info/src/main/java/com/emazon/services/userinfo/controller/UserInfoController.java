package com.emazon.services.userinfo.controller;

import com.emazon.services.userinfo.entity.UserInfo;
import com.emazon.services.userinfo.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class UserInfoController {
    private final UserInfoService userInfoService;

    @GetMapping("/")
    public CollectionModel<EntityModel<UserInfo>> all() {
        return userInfoService.getCustomers();
    }

    @GetMapping("/get/{customerId}")
    public EntityModel<UserInfo> one(@PathVariable String customerId) {
        return userInfoService.getCustomerById(customerId);
    }

    @PostMapping("/add")
    public EntityModel<UserInfo> addNewCustomer(@RequestBody UserInfo userInfo){
        return userInfoService.createNewCustomer(userInfo);
    }
}
