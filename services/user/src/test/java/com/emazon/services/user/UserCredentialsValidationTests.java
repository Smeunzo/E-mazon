package com.emazon.services.user;

import com.emazon.services.user.entity.Role;
import com.emazon.services.user.entity.UserCredentials;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserCredentialsValidationTests {

    private static Validator validator;
    private static UserCredentials validUser;

    @BeforeEach
    void initValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeAll
    static void createUser(){
        validUser = new UserCredentials(null,"abdelhakim","motdepasse",new ArrayList<>());
    }

    @Test
    public void shouldBeAValidUser(){
        Set<ConstraintViolation<UserCredentials>> violations = validator.validate(validUser);
        assertTrue(violations.isEmpty());
    }

    @Test
    void emptyUserShouldBeInvalid(){
        UserCredentials emptyUser = new UserCredentials();
        Set<ConstraintViolation<UserCredentials>> violations = getConstraintViolations(emptyUser);
        assertFalse(violations.isEmpty());
        assertEquals(2,violations.size());
    }

    @Test
    void userNameWithSpecialCharShouldBeInvalid(){
        UserCredentials user = new UserCredentials(null,validUser.getUsername()+"-",validUser.getPassword(),new ArrayList<>());

        Set<ConstraintViolation<UserCredentials>> violations = getConstraintViolations(user);
        assertFalse(violations.isEmpty());
        assertEquals(1,violations.size());

    }

    @Test
    void userNameShouldBeBetweenFourAndSixteenChar(){
        UserCredentials user = new UserCredentials(null,"123",validUser.getPassword(),new ArrayList<>());

        Set<ConstraintViolation<UserCredentials>> violations = getConstraintViolations(user);
        assertFalse(violations.isEmpty());
        assertEquals(1,violations.size());

        user.setUsername("12345678912345678");
        violations = getConstraintViolations(user);
        assertFalse(violations.isEmpty());
        assertEquals(1,violations.size());
    }

    @Test
    void passwordShouldAtLeastEightChar(){
        UserCredentials user = new UserCredentials(null,validUser.getUsername(),"1234567",new ArrayList<>());

        Set<ConstraintViolation<UserCredentials>> violations = getConstraintViolations(user);
        assertFalse(violations.isEmpty());
        assertEquals(1,violations.size());

        user.setPassword("12345678");
        violations = getConstraintViolations(user);
        assertEquals(0,violations.size());
    }

    private <T> Set<ConstraintViolation<T>> getConstraintViolations(T t) {
        return validator.validate(t);
    }
}
