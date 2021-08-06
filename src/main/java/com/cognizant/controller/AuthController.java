package com.cognizant.controller;

import com.cognizant.exception.InvalidUsernameException;
import com.cognizant.exception.LoginFailedException;
import com.cognizant.model.AuthResponse;
import com.cognizant.model.ProjectManager;
import com.cognizant.model.UserCredentials;
import com.cognizant.service.JwtUtil;
import com.cognizant.service.ManagerDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin("*")
public class AuthController {

	
	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	private ManagerDetailsService managerDetailsService;

	@Autowired
	Environment env;

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody UserCredentials userLoginCredentials) throws Exception {

		UserDetails userDetails = null;
		ProjectManager projectManager = null;
		String userId = userLoginCredentials.getUserId();
		String password = userLoginCredentials.getPassword();
		String orgPassword = null;
		String token = null;

		log.info("/login <= " + userLoginCredentials.toString());
		
		try {
			userDetails = managerDetailsService.loadUserByUsername(userId);
		}catch(UsernameNotFoundException e) {
			log.error("/login <= " + e.getMessage());
			throw new InvalidUsernameException(e.getMessage());
		}
		orgPassword = userDetails.getPassword();

		if (orgPassword.equals(password)) {
			token = jwtutil.generateToken((userDetails));
			projectManager = new ProjectManager(userId, password, token);
			managerDetailsService.saveUser(projectManager);
			log.info("/login => " + projectManager.toString());
			return new ResponseEntity<>(projectManager, HttpStatus.OK);
		} else {
			throw new LoginFailedException(env.getProperty("string.reason.loginfail"));
		}
	}

	@GetMapping(value = "/validate")
	public ResponseEntity<?> getValidity(@RequestHeader("Authorization") String token) {

		AuthResponse res = new AuthResponse();
		ResponseEntity<?> response = null;

		log.info("/validate <= " + token);
		token = token.substring(7); ///////////////////////////////////////////////////
		try {
			if (jwtutil.validateToken(token)) {
				res.setUid(jwtutil.extractUsername(token));
				res.setValid(true);
			}
		} catch (Exception e) {
			res.setValid(false);
			if (e.getMessage().contains(env.getProperty("token.expired")))
				response = new ResponseEntity<String>(env.getProperty("token.expired"), HttpStatus.FORBIDDEN);
			if (e.getMessage().contains(env.getProperty("auth.failed")))
				response = new ResponseEntity<String>(env.getProperty("auth.failed"), HttpStatus.FORBIDDEN);
			
			
			System.out.println(response);
			response = new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
			System.out.println(response);
			return response;
		}
		response = new ResponseEntity<AuthResponse>(res, HttpStatus.OK);
		log.info("/validate => " + res.toString());
		return response;
	}
}
