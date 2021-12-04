package com.olx.exception;

public class InvalidAuthTokenException extends RuntimeException {

	private String message = "";
	
	public InvalidAuthTokenException() {
		
	}
	
	public InvalidAuthTokenException(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "User is not authorized" + message;
	}

}
