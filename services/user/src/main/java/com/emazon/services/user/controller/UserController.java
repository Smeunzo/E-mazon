package com.emazon.services.user.controller;

import com.emazon.services.user.entity.Role;
import com.emazon.services.user.entity.UserCredentials;
import com.emazon.services.user.entity.UsernameAndRolesName;
import com.emazon.services.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
class UserController {

    private final UserService userService;

    @GetMapping(path = "/")
    public Collection<UserCredentials> getUsers() {
        return userService.loadUsers();
    }

    @PostMapping(path = "/")
    public UserCredentials addUser(@RequestBody UserCredentials userCredentials) {
        return userService.addNewUser(userCredentials);
    }

    @PostMapping(path = "/addRole")
    public Role addRole(@RequestBody Role role) {
        return userService.addNewRole(role);
    }

    @PostMapping(path = "/addRoleToUser")
    public void addRoleToUser(@RequestBody UsernameAndRolesName usernameAndRolesName){
        userService.addRoleToUserByUsername(usernameAndRolesName.getUsername(),usernameAndRolesName.getRolesname());
    }


}
