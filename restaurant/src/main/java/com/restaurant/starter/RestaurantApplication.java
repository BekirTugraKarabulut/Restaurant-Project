package com.restaurant.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.restaurant"})
@ComponentScan(basePackages = {"com.restaurant"})
@EnableJpaRepositories(basePackages = {"com.restaurant"})
@EnableCaching
@SpringBootApplication
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}
	
}
