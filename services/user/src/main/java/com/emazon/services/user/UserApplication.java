package com.emazon.services.user;

import com.emazon.services.user.entity.Role;
import com.emazon.services.user.entity.UserCredentials;
import com.emazon.services.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(UserService userService){
        return args -> {
            UserCredentials users1 = new UserCredentials(null,"hakim","hakim", new ArrayList<>());
            UserCredentials users2 = new UserCredentials(null,"mhamed","hakim", new ArrayList<>());
            UserCredentials users3 = new UserCredentials(null,"mehdi","hakim", new ArrayList<>());
            UserCredentials users4 = new UserCredentials(null,"faycoil","hakim", new ArrayList<>());
            UserCredentials users5 = new UserCredentials(null,"benoit","hakim", new ArrayList<>());
            UserCredentials users6 = new UserCredentials(null,"francois","hakim", new ArrayList<>());

            Role roleUser = new Role(null,"USER");
            Role roleAdmin = new Role(null,"ADMIN");

            userService.addNewUser(users1);
            userService.addNewUser(users2);
            userService.addNewUser(users3);
            userService.addNewUser(users4);
            userService.addNewUser(users5);
            userService.addNewUser(users6);

            userService.addNewRole(roleUser);
            userService.addNewRole(roleAdmin);

            userService.addRoleToUserByUsername("hakim","USER");
            userService.addRoleToUserByUsername("mhamed","USER");
            userService.addRoleToUserByUsername("mehdi","USER");
            userService.addRoleToUserByUsername("faycoil","USER");
            userService.addRoleToUserByUsername("benoit","USER");
            userService.addRoleToUserByUsername("francois","USER");

            userService.addRoleToUserByUsername("hakim","ADMIN");


        };
    }

}
