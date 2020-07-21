package com.shop.restfull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

@EntityScan(basePackages="com.shop.restfull.model") 
class SpringBootWebServer1ApplicationTests {

	@Test
	void contextLoads() {
	}

}
