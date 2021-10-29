package com.mayankmadhav.demo.app.mobileappws.utils;

import org.springframework.http.HttpStatus;

public class RestResponse<T> {

    private T body;
    private String message;
    private HttpStatus httpStatus;

    public RestResponse(T body, String message, HttpStatus httpStatus) {
        this.body = body;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public RestResponse(String message, HttpStatus httpStatus) {
        this.body = null;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
