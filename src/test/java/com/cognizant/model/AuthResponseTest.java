package com.cognizant.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author POD5

 *  For testing the AuthResponse 
 * 
 */

//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthResponseTest {

AuthResponse authResponse=new AuthResponse();

	@Test
	public void testAuthResponseConstructor()
	{
		AuthResponse response=new AuthResponse("username", true);
		assertEquals( "username" ,  response.getUid() );
	}

	@Test
	public void testUid()
	{
		authResponse.setUid("username");
		assertEquals("username" , authResponse.getUid() );
	}

	@Test
	public void testIsValid()
	{
		authResponse.setValid(true);
		assertEquals( true , authResponse.isValid());
	}

	@Test
	public void testtoString() 
	{
        String s = authResponse.toString();
        assertEquals( s , authResponse.toString());
    }

}
