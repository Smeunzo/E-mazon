package com.emazon.services.userinfo.controller;

import com.emazon.services.userinfo.entity.UserInfo;
import com.emazon.services.userinfo.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserInfoController {
    private final UserInfoService userInfoService;

    @GetMapping("/customers")
    public List<UserInfo> all() {
        return userInfoService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public UserInfo one(@PathVariable String customerId) {
        return userInfoService.getCustomerById(customerId);
    }

    @PostMapping("/customers")
    public UserInfo addNewCustomer(@RequestBody UserInfo userInfo){
        return userInfoService.createNewCustomer(userInfo);
    }
}
