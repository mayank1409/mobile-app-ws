package com.mayankmadhav.demo.app.mobileappws.exceptions;

import org.springframework.http.HttpStatus;

public class UserAuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 4909181603946638902L;
    private final static String MESSAGE = "Bad Credentials!";
    private final static HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;

    public UserAuthenticationException() {
        super(MESSAGE);
    }

    public UserAuthenticationException(String message) {
        this(MESSAGE, HTTP_STATUS);
    }

    public UserAuthenticationException(String message, HttpStatus httpStatus) {
    }

}
