package com.emazon.services.inventory;

import com.emazon.services.inventory.entity.Category;
import com.emazon.services.inventory.entity.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryValidationTests {

    private static Validator validator;
    private static Category category;

    @BeforeAll
    static void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldBeValidCategory() {
        category = new Category();
        category.setName("Phones");
        category.setDescription("Phones, Smartphones, Cellphones");

        Set<ConstraintViolation<Category>> violations = validator.validate(category);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldBeInvalidCategory() {
        category = new Category();

        Set<ConstraintViolation<Category>> violations = validator.validate(category);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
    }

    @Test
    void nameShouldNotBeBlank() {
        category = new Category();
        category.setName("");
        category.setDescription("Phones, Smartphones, Cellphones");

        Set<ConstraintViolation<Category>> violations = validator.validate(category);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
    }
}
