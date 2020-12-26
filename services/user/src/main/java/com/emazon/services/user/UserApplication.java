package com.emazon.services.user;

import com.emazon.services.user.dao.UserRepository;
import com.emazon.services.user.entity.User;
import com.emazon.services.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserService userService){
        return args -> {
            User hakim = new User(null,"Abdelhakim","belhachemi",21,"hakim@outlook.fr","hakim");
            User mhamed = new User(null,"mhamed","belhachemi",23,"mhamed@outlook.fr","mhamed");
            User faissoil = new User(null,"faissoil","said",44,"faissoil@outlook.fr","faissoil");
            userService.registUser(hakim);
            userService.registUser(mhamed);
            userService.registUser(faissoil);
        };
    }

}
