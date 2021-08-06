package com.cognizant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


@SpringBootTest 
public class JwtUtilTest {
	
	@InjectMocks
	JwtUtil jwtUtil;
	
	UserDetails userdetails;
	
	@Mock
	Environment env;
	
	@Test
	public void generateTokenTest() {
		when(env.getProperty("set.expire.token")).thenReturn("30");
		userdetails = new User("username", "pass", new ArrayList<>());
		String generateToken = jwtUtil.generateToken(userdetails);
		assertNotNull(generateToken);
	}
	
	@Test
	public void validateTokenTest() {
		userdetails = new User("username", "pass", new ArrayList<>());
		when(env.getProperty("set.expire.token")).thenReturn("30");
		String generateToken = jwtUtil.generateToken(userdetails);
		Boolean validateToken = jwtUtil.validateToken(generateToken);
		assertEquals(true, validateToken);
	}

	@Test
	public void validateTokenWithNameTest() {
		userdetails = new User("username", "pass", new ArrayList<>());
		when(env.getProperty("set.expire.token")).thenReturn("30");
		String generateToken = jwtUtil.generateToken(userdetails);
		Boolean validateToken = jwtUtil.validateToken(generateToken, userdetails);
		assertEquals(true, validateToken);
	}

	@Test
	public void validateTokenWithNameFalseTest() {
		userdetails = new User("username", "pass", new ArrayList<>());
		UserDetails user1 = new User("userName", "pass", new ArrayList<>());
		when(env.getProperty("set.expire.token")).thenReturn("30");
		String generateToken = jwtUtil.generateToken(userdetails);
		Boolean validateToken = jwtUtil.validateToken(generateToken, user1);
		assertEquals(false, validateToken);
	}

}
