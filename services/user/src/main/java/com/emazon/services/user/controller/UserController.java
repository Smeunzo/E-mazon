package com.emazon.services.user.controller;


import com.emazon.services.user.entity.User;
import com.emazon.services.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
class UserController {

    private final UserService userService;

    @GetMapping("/getUserByName")
    public User getCustomerByFirstNameAndLastName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        System.out.println(firstName);
        return userService.getUserByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/getUserById")
    public User getUserByFirstNameAndLastName(@RequestParam("id") String id) {
        return userService.getUserById(Long.parseLong(id));
    }

    @GetMapping("/")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/register")
    public User registUser(@RequestBody User user) {
        return userService.registUser(user);
    }



}
