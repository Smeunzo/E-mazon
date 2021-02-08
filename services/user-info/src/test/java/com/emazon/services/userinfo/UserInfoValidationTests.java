package com.emazon.services.userinfo;

import com.emazon.services.userinfo.entity.Address;
import com.emazon.services.userinfo.entity.UserInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoValidationTests {

	private static Validator validator;
	private static UserInfo userInfo;
	private static Address address;

	@BeforeEach
	void initValidator(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@BeforeAll
	static void createFakeUserInfo(){
		address = new Address();
		address.setStreetNumber(234);
		address.setStreetName("Rue Dupont");
		// locality can be null
		address.setLocality(null);
		address.setZipCode("75009");
		address.setCity("Paris");
		address.setCountry("France");

		userInfo = new UserInfo();
		userInfo.setCustomerId("U9ZZO2");
		userInfo.setFirstName("Jean");
		userInfo.setMiddleName("Dubois");
		userInfo.setLastName("Phillipe");
		userInfo.setEmail("jean.phillipe@example.com");
		userInfo.setBirthdate(LocalDate.of(1990,1,1));
		userInfo.setAddress(address);

	}

	@Test
	void shouldBeAValidAddress(){
		Set<ConstraintViolation<Address>> violations = validator.validate(address);

		assertTrue(violations.isEmpty());
	}

	@Test
	void shouldBeAValidUserInfo(){
		Set<ConstraintViolation<UserInfo>> violations = getConstraintViolations(userInfo);

		assertTrue(violations.isEmpty());
	}

	@Test
	void EmptyAddressShouldBeInvalid(){
		UserInfo u = new UserInfo();

		Set<ConstraintViolation<UserInfo>> violations = getConstraintViolations(u);

		assertFalse(violations.isEmpty());
		assertEquals(5,violations.size());
	}

	@Test
	void EmptyUserShouldBeInvalid(){
		UserInfo u = new UserInfo();

		Set<ConstraintViolation<UserInfo>> violations = getConstraintViolations(u);

		assertFalse(violations.isEmpty());
		assertEquals(5,violations.size());
	}


	private <T> Set<ConstraintViolation<T>> getConstraintViolations(T t) {
		return validator.validate(t);
	}
//	@Test
//	void shouldBeAValidAddress(){
//		Address address =  new Address(null,41,"street Name", "locality", "13013", "city", "country");
//		Set<ConstraintViolation<Address>> violations = validator.validate(address);
//		assertTrue(violations.isEmpty());
//	}
//
//	@Test
//	void shouldNotValidateEmptyAddress(){
//		Address address = new Address();
//		Set<ConstraintViolation<Address>> violations = validator.validate(address);
//
//		assertFalse(violations.isEmpty());
//
//		// locality can be null
//		assertEquals(5, violations.size());
//	}
//
//	@Test
//	void shouldBeAValidCustomer(){
//		Address address = new Address();
//
//		UserInfo userInfo = new UserInfo();
//		userInfo.setCustomerId(UUIDGenerator.generate());
//		userInfo.setFirstName("firstName");
//		userInfo.setLastName("lastName");
//		userInfo.setBirthdate(LocalDate.of(1999, Month.AUGUST,1));
//		userInfo.setEmail("email@example.com");
//		userInfo.setAddress(address);
//		// middleName can be null
//		userInfo.setMiddleName(null);
//
//		Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
//		assertTrue(violations.isEmpty());
//	}
}
