package com.cognizant.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;


@SpringBootTest
public class CustomErrorResponseTest {


	@Test
	public void testCustomErrorResponse() {

		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		assertNotNull(customErrorResponse);
	}


	@Test
	public void testCustomErrorResponseParameterized() {

		CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), HttpStatus.OK,
				"Message","reason");
		assertNotNull(customErrorResponse);

	}

	@Test
	public void testToStringMethod() {

		CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now(), HttpStatus.OK,
				"Message","reason");
		assertEquals(customErrorResponse.toString(), customErrorResponse.toString());
	}

	
	@Test
	public void testGetStatus() {

		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setStatus(HttpStatus.OK);
		assertEquals(HttpStatus.OK, customErrorResponse.getStatus());

	}

	@Test
	public void testGetMessage() {

		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setMessage("Message");
		assertEquals("Message", customErrorResponse.getMessage());

	}

	@Test
	public void testSetLocalDateTime() {

		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setTimestamp(LocalDateTime.now());

	}

	@Test
	public void testSetStatus() {

		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setStatus(HttpStatus.OK);

	}

	@Test
	public void testSetMessage() {

		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setMessage("Message");

	}

}
