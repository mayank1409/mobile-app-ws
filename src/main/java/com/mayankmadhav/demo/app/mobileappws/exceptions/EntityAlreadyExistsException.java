package com.mayankmadhav.demo.app.mobileappws.exceptions;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -7458517450360694785L;
	private static final String MESSAGE = "Entity Already Exists!";
	private static final HttpStatus HTTP_STATUS = HttpStatus.CONFLICT;

	public EntityAlreadyExistsException(String message, HttpStatus httpStatus) {
		super(message);
	}

	public EntityAlreadyExistsException() {
		this(MESSAGE, HTTP_STATUS);
	}

	public EntityAlreadyExistsException(String message) {
		this(MESSAGE, HTTP_STATUS);
	}

}
