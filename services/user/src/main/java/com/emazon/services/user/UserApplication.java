package com.emazon.services.user;
import com.emazon.services.user.entity.Role;
import com.emazon.services.user.entity.UserCredentials;
import com.emazon.services.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)// authorise prepost annotation
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
            Role roleUser = new Role(null,"USER");
            Role roleAdmin = new Role(null,"ADMIN");

            userService.addNewRole(roleUser);
            userService.addNewRole(roleAdmin);

            UserCredentials users1 = new UserCredentials(null,"hakim","hakim456", new ArrayList<>());
            UserCredentials users2 = new UserCredentials(null,"mhamed","mhamed456", new ArrayList<>());


            userService.addNewUser(users1);
            userService.addNewUser(users2);

            userService.addRoleToUserByUsername("hakim","ADMIN");

        };
    }

}
