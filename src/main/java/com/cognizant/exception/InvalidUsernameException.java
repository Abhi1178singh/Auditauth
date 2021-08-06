package com.cognizant.exception;

public class InvalidUsernameException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidUsernameException(String msg) {
		super(msg,null,false,false);
	}

}
