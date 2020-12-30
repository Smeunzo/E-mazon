package com.emazon.services.user.service;
import com.emazon.services.user.dao.RoleRepository;
import com.emazon.services.user.dao.UserRepository;
import com.emazon.services.user.entity.Role;
import com.emazon.services.user.entity.UserCredentials;
import com.emazon.services.user.exceptions.UsernameAlreadyInUseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Collection;


@Service
@Transactional
@Validated
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

    @Override
    public UserCredentials loadUserByUsername(String username){
        return userRepository.findUserCredentialsByUsername(username);

    }
    @Override
    public Role loadRoleByName(String rolesName){
        Role role = roleRepository.findRoleCredentialsByRolesName(rolesName);
        return role ;

    }
    @Override
    public UserCredentials addNewUser(UserCredentials userCredentials){

        validate(userCredentials);
        boolean containsUsername = userRepository.existsByUsername(userCredentials.getUsername());
        if(containsUsername)
            throw new UsernameAlreadyInUseException(userCredentials.getUsername());

        String password = userCredentials.getPassword();
        userCredentials.setPassword(passwordEncoder.encode(password));
        UserCredentials user = userRepository.save(userCredentials);
        addRoleToUserByUsername(user.getUsername(),"USER");
        return user ;
    }

     private void validate (@Valid @SuppressWarnings(value = "unused") UserCredentials userCredentials){ }

    @Override
    public Role addNewRole(Role role){
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUserByUsername(String username, String rolesName){
        UserCredentials userCredentials = loadUserByUsername(username);
        Role role = loadRoleByName(rolesName);
        userCredentials.getRolesOfUser().add(role);
    }
}

