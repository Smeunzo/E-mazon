package com.emazon.services.userinfo;

import com.emazon.services.userinfo.entity.Address;
import com.emazon.services.userinfo.entity.UserInfo;
import com.emazon.services.userinfo.utils.UUIDGenerator;
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

class UserInfoTests {

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

		UserInfo userInfo = new UserInfo();
		userInfo.setCustomerId(UUIDGenerator.generate());
		userInfo.setFirstName("firstName");
		userInfo.setLastName("lastName");
		userInfo.setBirthdate(LocalDate.of(1999, Month.AUGUST,1));
		userInfo.setEmail("email@example.com");
		userInfo.setAddress(address);
		// middleName can be null
		userInfo.setMiddleName(null);

		Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
		assertTrue(violations.isEmpty());
	}
}
