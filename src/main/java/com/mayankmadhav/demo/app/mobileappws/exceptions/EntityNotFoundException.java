package com.mayankmadhav.demo.app.mobileappws.exceptions;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5696729732747370054L;
	private static final String MESSAGE = "Entity Not Found!";


	public EntityNotFoundException(String message) {
		super(message);
	}
	
	public EntityNotFoundException() {
		this(MESSAGE);
	}
 
}
