package com.revature.customExceptions;

public class UserNotFoundException extends Exception{

	public UserNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
