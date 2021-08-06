package com.cognizant.service;

import com.cognizant.model.ProjectManager;
import com.cognizant.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ManagerDetailsService implements UserDetailsService {

	@Autowired
	private ManagerRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ProjectManager manager = null;
		UserDetails userDetails =null;

		manager = repository.findById(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email: " + username));
		
		userDetails=new User(manager.getUserId(), manager.getPassword(), new ArrayList<>());
		return userDetails; 
	}

	public void saveUser(ProjectManager projectManager) {
		repository.save(projectManager);
	}
}
