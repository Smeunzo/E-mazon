package com.emazon.services.user.service;

import com.emazon.services.user.entity.Role;
import com.emazon.services.user.entity.UserCredentials;

import java.util.Collection;

public interface UserServicesInterface {

    Collection<UserCredentials> loadUsers();
    UserCredentials loadUserByUsername(String username);
    Role loadRoleByName(String rolesName);
    UserCredentials addNewUser(UserCredentials userCredentials);
    Role addNewRole(Role role);
    void addRoleToUserByUsername(String username, String rolesName );

}
