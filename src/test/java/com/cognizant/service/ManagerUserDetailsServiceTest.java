package com.cognizant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.model.ProjectManager;
import com.cognizant.repository.ManagerRepository;


@SpringBootTest
public class ManagerUserDetailsServiceTest {

UserDetails userdetails;
	
	@InjectMocks
	ManagerDetailsService managerdetailservice;

	@Mock
	ManagerRepository userservice;

	@Test
	public void loadUserByUsernameTest() {
		
		ProjectManager user1=new ProjectManager("username","pass",null);
//		ProjectManager user2 = null;
		Optional<ProjectManager> data =Optional.of(user1) ;
		when(userservice.findById("username")).thenReturn(data);
		UserDetails loadUserByUsername2 = managerdetailservice.loadUserByUsername("username");
		assertEquals(user1.getUserId(),loadUserByUsername2.getUsername());
	}

}
