package com.emazon.services.user.controller;

import com.emazon.services.user.entity.Role;
import com.emazon.services.user.entity.UserCredentials;
import com.emazon.services.user.util.UsernameAndRolesName;
import com.emazon.services.user.service.JWTService;
import com.emazon.services.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@RestController
@AllArgsConstructor
class UserController {

    private final UserService userService;
    private final JWTService jwtService;

    @GetMapping(path = "/users")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Collection<UserCredentials> getUsers() {
        return userService.loadUsers();
    }

    @PostMapping(path = "/users/registerUser")
    public UserCredentials addUser(@RequestBody UserCredentials userCredentials) {
        return userService.addNewUser(userCredentials);
    }

    @PostMapping(path = "/users/addRole")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Role addRole(@RequestBody Role role) {
        return userService.addNewRole(role);
    }

    @PostMapping(path = "/users/addRoleToUser")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void addRoleToUser(@RequestBody UsernameAndRolesName usernameAndRolesName) {
        userService.addRoleToUserByUsername(usernameAndRolesName.getUsername(), usernameAndRolesName.getRolesname());
    }

    @GetMapping(path = "/users/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
        jwtService.refreshToken(request,response);
    }

}
