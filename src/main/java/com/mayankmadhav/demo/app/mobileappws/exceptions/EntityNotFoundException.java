package com.mayankmadhav.demo.app.mobileappws.exceptions;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5696729732747370054L;
	private static final String MESSAGE = "Entity Not Found!";
	String message;

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException() {
		super(MESSAGE);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
