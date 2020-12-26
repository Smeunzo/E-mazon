package com.emazon.services.user.service;
import com.emazon.services.user.dao.UserRepository;
import com.emazon.services.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User getUserById(long id) {
        return userRepository.findById(id);
    }

    public User getUserByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.getUserByFirstNameAndLastName(firstName, lastName);
    }

    public User registUser(User user) {
        if(userRepository.existsUserByEmail(user.getEmail()))
            return null ;

        User copyUser = new User(null,user.getFirstName(), user.getLastName(), user.getAge(), user.getEmail(),passwordEncoder.encode(user.getPassword()));
        return userRepository.save(copyUser);

    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }





}
