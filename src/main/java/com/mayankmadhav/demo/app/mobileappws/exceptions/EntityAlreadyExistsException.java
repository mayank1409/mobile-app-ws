package com.mayankmadhav.demo.app.mobileappws.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -7458517450360694785L;
	private static final String MESSAGE = "Entity Already Exists!";

	private String message;

	public EntityAlreadyExistsException() {
		super(MESSAGE);
	}

	public EntityAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
