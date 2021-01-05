package com.emazon.services.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	@Bean
	RouteLocator routes(RouteLocatorBuilder builder){
		return builder.routes()
				.route(r->r.path("/customers/**").uri("lb://USER_INFO-SERVICE").id("r1"))
				.route(r->r.path("/inventory/**").uri("lb://INVENTORY-SERVICE").id("r2"))
				.route(r->r.path("/users/**").uri("lb://USER-SERVICE").id("r3"))
				.route(r->r.path("/login/**").uri("lb://USER-SERVICE").id("r4"))
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
