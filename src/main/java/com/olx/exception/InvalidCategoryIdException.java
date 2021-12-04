package com.olx.exception;

public class InvalidCategoryIdException extends RuntimeException {

	private String message = "";
	public InvalidCategoryIdException() {}
	public InvalidCategoryIdException(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "InvalidCategoryIdException" + message;
	}

}
