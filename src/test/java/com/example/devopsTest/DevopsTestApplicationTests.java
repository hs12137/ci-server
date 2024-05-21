package com.example.devopsTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AssertionErrors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DevopsTestApplicationTests {
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.url}")
	private String url;

	@Test
	void contextLoads() {
	}
	@Test
	void dbIsH2(){
		assertEquals("sa", username);
		assertEquals("jdbc:h2:mem:test", url);
	}
}
