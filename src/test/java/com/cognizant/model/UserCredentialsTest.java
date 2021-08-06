package com.cognizant.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserCredentialsTest {

	UserCredentials loginCredential = new UserCredentials();
	
	@Test
	public void testUserLoginCredentialAllConstructor() {
		UserCredentials credential = new UserCredentials("username", "pass");
		assertEquals(credential.getUserId(), "username");
	}

	@Test
	public void testGetUid() {
		
		loginCredential.setUserId("username");
		assertEquals(loginCredential.getUserId(), "username");
	}

	@Test
	public void testUserPassword() {
		loginCredential.setPassword("pass");
		assertEquals(loginCredential.getPassword(), "pass");
	}

	@Test
	public void testoString() {
		String string = loginCredential.toString();
		assertEquals(loginCredential.toString(), string);
	}

}
