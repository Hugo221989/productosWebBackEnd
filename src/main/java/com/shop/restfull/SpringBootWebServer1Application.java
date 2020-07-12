package com.shop.restfull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootWebServer1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebServer1Application.class, args);
	}

}
