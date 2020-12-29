package com.emazon.services.user.service;
import com.emazon.services.user.dao.RoleRepository;
import com.emazon.services.user.dao.UserRepository;
import com.emazon.services.user.entity.Role;
import com.emazon.services.user.entity.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;


@Service
@Transactional
public class UserService implements UserServicesInterface{


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder ;


    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder ;
    }

    @Override
    public Collection<UserCredentials> loadUsers() {
        return userRepository.findAll();
    }


    public UserCredentials loadUserByUsername(String username){
        return userRepository.findUserCredentialsByUsername(username);

    }

    public Role loadRoleByName(String rolesName){
        Role role = roleRepository.findRoleCredentialsByRolesName(rolesName);
        System.out.println(role);
        return role ;

    }

    public UserCredentials addNewUser(UserCredentials userCredentials){
        String password = userCredentials.getPassword();
        userCredentials.setPassword(passwordEncoder.encode(password));
        return userRepository.save(userCredentials);
    }

    public Role addNewRole(Role role){
        return roleRepository.save(role);
    }

    public void addRoleToUserByUsername(String username, String rolesName){
        UserCredentials userCredentials = loadUserByUsername(username);
        Role role = loadRoleByName(rolesName);
        System.out.println("avant : "+userCredentials);
        userCredentials.getRolesOfUser().add(role);
        System.out.println("apres : "+userCredentials);
    }




}

