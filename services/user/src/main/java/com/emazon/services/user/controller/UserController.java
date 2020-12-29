package com.emazon.services.user.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.emazon.services.user.entity.Role;
import com.emazon.services.user.entity.UserCredentials;
import com.emazon.services.user.entity.UsernameAndRolesName;
import com.emazon.services.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
class UserController {

    private final UserService userService;

    @GetMapping(path = "/users")
    //@PostAuthorize("hasAuthority('USER')")
    public Collection<UserCredentials> getUsers() {
        return userService.loadUsers();
    }

    @PostMapping(path = "/registerUser")

    public UserCredentials addUser(@RequestBody UserCredentials userCredentials) {
        return userService.addNewUser(userCredentials);
    }

    @PostMapping(path = "/addRole")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Role addRole(@RequestBody Role role) {
        return userService.addNewRole(role);
    }

    @PostMapping(path = "/addRoleToUser")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void addRoleToUser(@RequestBody UsernameAndRolesName usernameAndRolesName) {

        userService.addRoleToUserByUsername(usernameAndRolesName.getUsername(), usernameAndRolesName.getRolesname());
    }

    // TODO refactor code
    @GetMapping(path = "/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String authToken = request.getHeader("Authorization");
        if (authToken != null && authToken.startsWith("Bearer ")) {
            try {
                String refreshToken = authToken.substring(7);
                Algorithm algorithm1 = Algorithm.HMAC256("mySecret1234");
                JWTVerifier jwtVerifier = JWT.require(algorithm1).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                UserCredentials userCredentials = userService.loadUserByUsername(username);
                String jwtAccessToken = JWT.create()
                        .withSubject(userCredentials.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+5*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",userCredentials.getRolesOfUser().stream().map(ga->ga.getRolesName()).collect(Collectors.toList()))
                        .sign(algorithm1);
                Map<String,String> idToken = new HashMap<>();
                idToken.put("access-token",jwtAccessToken);
                idToken.put("refresh-token",refreshToken);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);
                response.setHeader("Authorization",jwtAccessToken);
            }
            catch (Exception e){
                throw e ;
            }

        }
        else{
            throw new RuntimeException("refresh token required");
        }
    }


}
