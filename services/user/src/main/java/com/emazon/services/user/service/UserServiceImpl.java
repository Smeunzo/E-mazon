package com.emazon.services.user.service;

import com.emazon.services.user.dao.RoleRepository;
import com.emazon.services.user.dao.UserRepository;
import com.emazon.services.user.entity.Role;
import com.emazon.services.user.entity.UserCredentials;
import com.emazon.services.user.exceptions.RoleAlreadyExistsException;
import com.emazon.services.user.exceptions.UsernameAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Collection;


@Service
@Validated
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder ;

    @Override
    public Collection<UserCredentials> loadUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserCredentials loadUserByUsername(String username){
        return userRepository.findUserCredentialsByUsername(username);
    }

    @Override
    public Role loadRoleByName(String rolesName){
        return roleRepository.findRoleCredentialsByRolesName(rolesName);
    }

    @Override
    @Transactional
    public UserCredentials addNewUser(UserCredentials userCredentials){

        validate(userCredentials);
        boolean containsUsername = userRepository.existsByUsername(userCredentials.getUsername());
        if(containsUsername)
            throw new UsernameAlreadyExistsException(userCredentials.getUsername());

        String password = userCredentials.getPassword();
        userCredentials.setPassword(passwordEncoder.encode(password));
        UserCredentials user = userRepository.save(userCredentials);
        addRoleToUserByUsername(user.getUsername(),"USER");
        return user ;
    }

    private <T> void validate (@Valid @SuppressWarnings(value = "unused")T t){ }


    @Override
    @Transactional
    public Role addNewRole(Role role){
        validate(role);
        if(roleRepository.existsRoleByRolesName(role.getRolesName()))
            throw new RoleAlreadyExistsException(role.getRolesName());
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public void addRoleToUserByUsername(String username, String rolesName){
        UserCredentials userCredentials = loadUserByUsername(username);
        Role role = loadRoleByName(rolesName);
        userCredentials.getRolesOfUser().add(role);
    }
}

