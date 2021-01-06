package com.emazon.services.inventory;

import com.emazon.services.inventory.entity.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ProductionValidationTests {

    private static Validator validator;
    private static Product p ;

    @BeforeAll
    static void  init(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldBeAValidProduct(){
        p = new Product();
        p.setName("Nokia 3310");
        p.setPrice(600);
        p.setStock(5);

        Set<ConstraintViolation<Product>> violations = validator.validate(p);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldBeInvalidProduct(){
        p = new Product();
        Set<ConstraintViolation<Product>> violations = validator.validate(p);
        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());
    }

    @Test
    void priceShouldBePositif(){
        p = new Product();
        p.setName("Nokia 3310");
        p.setStock(2);
        p.setPrice(0);

        Set<ConstraintViolation<Product>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());
        assertEquals(1,violations.size());

        p.setPrice(-0.01);
        violations = validator.validate(p);

        assertFalse(violations.isEmpty());
        assertEquals(1,violations.size());

        p.setPrice(699);
        violations = validator.validate(p);

        assertTrue(violations.isEmpty());
    }

    @Test
    void stockShouldBePositiveOrZero(){
        p = new Product();
        p.setName("Nokia 3310");
        p.setPrice(699);
        p.setStock(-1);

        Set<ConstraintViolation<Product>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());
        assertEquals(1,violations.size());

        p.setStock(0);
        violations = validator.validate(p);

        assertTrue(violations.isEmpty());

        p.setStock(1);
        violations = validator.validate(p);

        assertTrue(violations.isEmpty());

    }

    @Test
    void nameShouldNotBeBlank(){
        p = new Product();
        p.setStock(3);
        p.setPrice(699);
        p.setName("");

        Set<ConstraintViolation<Product>> violations = validator.validate(p);

        assertFalse(violations.isEmpty());
        assertEquals(1,violations.size());

        p.setName("Nokia 3310");

        violations = validator.validate(p);

        assertTrue(violations.isEmpty());
    }
}
