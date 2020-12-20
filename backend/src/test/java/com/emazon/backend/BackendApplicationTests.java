package com.emazon.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest
class BackendApplicationTests {

	@Autowired
	HomeController homeController;

	@Test
	void contextLoads() {
		assertThat(homeController).isNotNull();
		assertThat(homeController.message().getMessage()).isEqualTo("message");
	}

}
