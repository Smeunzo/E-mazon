package com.emazon.services.user;

import com.emazon.services.user.entity.Role;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RoleValidationTests {

    private static Validator validator;
    private static Role validRole;

    @BeforeEach
    void initValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeAll
    static void createRole(){
        validRole = new Role(null,"ADMIN");
    }

    @Test
    public void shouldBeAValidRole(){

        Set<ConstraintViolation<Role>> violations = validator.validate(validRole);
        assertTrue(violations.isEmpty());

    }

    @Test
    void emptyRolesNameShouldBeInvalid(){
        Role emptyRole = new Role();
        Set<ConstraintViolation<Role>> violations = getConstraintViolations(emptyRole);
        assertFalse(violations.isEmpty());
        assertEquals(1,violations.size());
    }

    @Test
    void roleShouldNoyContainsNumber(){
        Role role = new Role(null, validRole.getRolesName()+"1");

        Set<ConstraintViolation<Role>> violations = getConstraintViolations(role);
        assertFalse(violations.isEmpty());
        assertEquals(1,violations.size());
    }

    private <T> Set<ConstraintViolation<T>> getConstraintViolations(T t) {
        return validator.validate(t);
    }
}
