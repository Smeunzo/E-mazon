package com.emazon.services.user;


import com.emazon.services.user.dao.RoleRepository;
import com.emazon.services.user.dao.UserRepository;
import com.emazon.services.user.entity.Role;
import com.emazon.services.user.entity.UserCredentials;
import com.emazon.services.user.exceptions.RoleAlreadyExistsException;
import com.emazon.services.user.exceptions.UsernameAlreadyExistsException;
import com.emazon.services.user.service.UserService;
import com.emazon.services.user.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTests {

    private static UserCredentials user1;
    private static UserCredentials user2;
    private static UserCredentials user3;
    private static UserCredentials user4;
    private static UserCredentials newUser ;
    private static UserCredentials userAddedInRepository ;

    private static Role role1 ;
    private static Role role2 ;
    private static Role newRole ;
    private static Role roleAlreadyAddedInRepository ;

    @BeforeAll
    static void createUsers(){
        role1 = new Role(null,"USER");
        role2 = new Role(null,"ADMIN");
        newRole = new Role(null,"HACK");
        roleAlreadyAddedInRepository = new Role(null,"ROLEALREADYEXIST");

        user1 = new UserCredentials(null,"abdelhakim","hakim",new ArrayList<>());
        user2 = new UserCredentials(null,"mhamed12","mhamed",new ArrayList<>());
        user3 = new UserCredentials(null,"faycoil1","faycoil",new ArrayList<>());
        user4 = new UserCredentials(null,"mehdi123","mehdi",new ArrayList<>());
        newUser = new UserCredentials(null,"abdelkader","abdelkader",new ArrayList<>());
        userAddedInRepository = new UserCredentials(null,"abdelkarim","motdepasse",new ArrayList<>());
        newUser.getRolesOfUser().add(role1);
        
    }

    private static final UserRepository userRepository = mock(UserRepository.class);
    private static final RoleRepository roleRepository = mock(RoleRepository.class);

    @BeforeEach
    void mockRepositories() {
        when(userRepository.save(newUser))
                .thenReturn(newUser);


        when(userRepository.findUserCredentialsByUsername(user1.getUsername()))
                .thenReturn(user1);
        when(userRepository.findUserCredentialsByUsername(user2.getUsername()))
                .thenReturn(user2);
        when(userRepository.findUserCredentialsByUsername(user3.getUsername()))
                .thenReturn(user3);
        when(userRepository.findUserCredentialsByUsername(user4.getUsername()))
                .thenReturn(user4);
        when(userRepository.findUserCredentialsByUsername(newUser.getUsername()))
                .thenReturn(newUser);

        when(userRepository.existsByUsername(user1.getUsername()))
                .thenReturn(true);
        when(userRepository.existsByUsername(user2.getUsername()))
                .thenReturn(true);
        when(userRepository.existsByUsername(user3.getUsername()))
                .thenReturn(true);
        when(userRepository.existsByUsername(user3.getUsername()))
                .thenReturn(true);


        when(userRepository.findAll())
                .thenReturn(List.of(user1, user2, user3,user4));


        when(roleRepository.save(newRole))
                .thenReturn(newRole);


        when(roleRepository.findRoleCredentialsByRolesName(role1.getRolesName()))
                .thenReturn(role1);
        when(roleRepository.findRoleCredentialsByRolesName(role2.getRolesName()))
                .thenReturn(role2);
        when(roleRepository.findRoleCredentialsByRolesName("USER"))
                .thenReturn(role1);


        when(roleRepository.existsRoleByRolesName(role1.getRolesName()))
                .thenReturn(true);
        when(roleRepository.existsRoleByRolesName(role2.getRolesName()))
                .thenReturn(true);

    }

    @BeforeAll
    static void mockThrows(){
        when(userRepository.save(userAddedInRepository))
                .thenThrow(UsernameAlreadyExistsException.class);

        when(roleRepository.save(roleAlreadyAddedInRepository))
                .thenThrow(RoleAlreadyExistsException.class);
    }

    UserService userService = new UserServiceImpl(userRepository,roleRepository, new BCryptPasswordEncoder());

    @Test
    void shouldReturnAllUsers(){
        Collection<UserCredentials> userslist = userService.loadUsers();

        assertEquals(List.of(user1,user2,user3,user4),userslist);
    }

    @Test
    void shouldReturnUserByUsername(){
        UserCredentials user = userService.loadUserByUsername(user1.getUsername());
        assertEquals(user1,user);

        user = userService.loadUserByUsername(user2.getUsername());
        assertEquals(user2,user);

        user = userService.loadUserByUsername(user3.getUsername());
        assertEquals(user3,user);

        user = userService.loadUserByUsername(user4.getUsername());
        assertEquals(user4,user);
    }

    @Test
    void shouldReturnRoleByRolesName(){
        Role role = userService.loadRoleByName(role1.getRolesName());
        assertEquals(role1,role);

        role = userService.loadRoleByName(role2.getRolesName());
        assertEquals(role2,role);
    }

    @Test
    void shouldAddRoleToUser(){
        assertFalse(user1.getRolesOfUser().contains(role1));

        userService.addRoleToUserByUsername(user1.getUsername(),role1.getRolesName());
        assertTrue(user1.getRolesOfUser().contains(role1));
    }

    @Test
    void shouldCreateNewUser(){
        UserCredentials user = userService.addNewUser(newUser);
        assertEquals(user,newUser);
    }

    @Test
    void shouldCreateNewRole(){
        Role role = userService.addNewRole(newRole);
        assertEquals(newRole,role);
    }

    @Test
    void shouldThrowUserAlreadyExistsException(){
        assertThrows(UsernameAlreadyExistsException.class,()->{userService.addNewUser(userAddedInRepository);});
    }

    @Test
    void shouldThrowRoleAlreadyExists(){
        assertThrows(RoleAlreadyExistsException.class,()->{userService.addNewRole(roleAlreadyAddedInRepository);});
    }
}
