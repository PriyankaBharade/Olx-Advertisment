package com.olx.exception;

public class InvalidAdvertismentIdException  extends RuntimeException {

	private String message = "";
	public InvalidAdvertismentIdException() {}
	public InvalidAdvertismentIdException(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "InvalidAdvertismentIdException" + message;
	}


}
