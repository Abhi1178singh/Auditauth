package com.cognizant.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProjectManagerTest {
	
	ProjectManager projectManager = new ProjectManager();

	@Test
	public void testProjectManagerAllConstructor()
	{
		ProjectManager manager=new ProjectManager("username", "password", null);
		assertEquals( "username" , manager.getUserId());
	}

	@Test
	public void testUserid()
	{
		projectManager.setUserId("username");
		assertEquals( "username",  projectManager.getUserId());
	}
	
	@Test
	public void testUserPassword()
	{
		projectManager.setPassword("pass");
		assertEquals( "pass" , projectManager.getPassword());
	}

	@Test
	public void testAuthToken()
	{
		projectManager.setAuthToken("xyz");
		assertEquals("xyz", projectManager.getAuthToken());
	}
	
	@Test
	public void testToString() {
		String string = projectManager.toString();
		assertEquals(string , projectManager.toString());
	}

}
