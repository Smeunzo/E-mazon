package com.emazon.services.customer;

import com.emazon.services.customer.entity.Address;
import com.emazon.services.customer.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTests {

	private Validator validator;

	@BeforeEach
	void init(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void shouldBeAValidAddress(){
		Address address =  new Address(null,41,"street Name", "locality", "13013", "city", "country");
		Set<ConstraintViolation<Address>> violations = validator.validate(address);
		assertTrue(violations.isEmpty());
	}

	@Test
	void shouldNotValidateEmptyAddress(){
		Address address = new Address();
		Set<ConstraintViolation<Address>> violations = validator.validate(address);

		assertFalse(violations.isEmpty());

		// locality can be null
		assertEquals(5, violations.size());
	}

	@Test
	void shouldBeAValidCustomer(){
		Address address = new Address();

		Customer customer = new Customer();
		customer.setFirstName("firstName");
		customer.setLastName("lastName");
		customer.setBirthdate(LocalDate.of(1999, Month.AUGUST,1));
		customer.setEmail("email@example.com");
		customer.setAddress(address);
		// middleName can be null
		customer.setMiddleName(null);

		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertTrue(violations.isEmpty());
	}
}
