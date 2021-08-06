package com.cognizant;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuditAuthenticationApplicationTests {
	
	@Mock
	AuditAuthenticationApplication application;

	@Test
	public void contextLoads() {
		assertNotNull(application);
	}

}
