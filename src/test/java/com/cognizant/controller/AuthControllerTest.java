package com.cognizant.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.exception.LoginFailedException;
import com.cognizant.model.AuthResponse;
import com.cognizant.model.ProjectManager;
import com.cognizant.model.UserCredentials;
import com.cognizant.repository.ManagerRepository;
import com.cognizant.service.JwtUtil;
import com.cognizant.service.ManagerDetailsService;

@SpringBootTest
public class AuthControllerTest {

	@InjectMocks
	AuthController authController;

	AuthResponse authResponse;

	UserDetails userdetails;

	@Mock
	JwtUtil jwtutil;
	
	@Mock
	ManagerDetailsService managerdetailservice;

	@Mock
	ManagerRepository managerservice;
	
	@Mock
	Environment env;
	
	@Test
	public void loginTest() throws Exception {
		
		UserCredentials user = new UserCredentials("username", "pass");
		
		UserDetails loadUserByUsername = managerdetailservice.loadUserByUsername("username");
		UserDetails value = new User(user.getUserId(), user.getPassword(), new ArrayList<>());
		
		when(managerdetailservice.loadUserByUsername("username")).thenReturn(value);
		when(jwtutil.generateToken(loadUserByUsername)).thenReturn("token");
		
		ResponseEntity<?> login = authController.login(user);
		assertEquals( 200 , login.getStatusCodeValue() );
	}

	@Test
	public void invalidLoginTest() throws LoginFailedException, Exception {

		UserCredentials user = new UserCredentials("username", "pass");
		UserDetails loadUserByUsername = managerdetailservice.loadUserByUsername("username");
		UserDetails value = new User(user.getUserId(), "username1", new ArrayList<>());
		when(managerdetailservice.loadUserByUsername("username")).thenReturn(value);
		when(jwtutil.generateToken(loadUserByUsername)).thenReturn("token");
		assertThrows(LoginFailedException.class, ()-> when(authController.login(user)).thenThrow(new LoginFailedException()));
	}

	@Test
	public void validateTestValidtoken() {

		when(jwtutil.validateToken("token")).thenReturn(true);
		when(jwtutil.extractUsername("token")).thenReturn("username");
		ProjectManager user1 = new ProjectManager("username", "password", "xyz");
		Optional<ProjectManager> data = Optional.of(user1);
		when(managerservice.findById("username")).thenReturn(data);
		ResponseEntity<?> validity = authController.getValidity("bearer token");
		assertEquals(validity.getBody().toString().contains("true"), true);

	}

	@Test
	public void validateTestInValidtoken() {
		when(jwtutil.validateToken("token")).thenReturn(false);
		ResponseEntity<?> validity = authController.getValidity("bearer token");
		assertEquals( true ,  validity.getBody().toString().contains("false") );
	}
}
