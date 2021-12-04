package com.mayankmadhav.demo.app.mobileappws.utils;

import org.springframework.http.HttpStatus;

public class RestResponse<T> {

    private T body;
    private String message;
    private HttpStatus httpStatus;
    private long timestamp;
    private boolean error;

    public RestResponse(T body, String message, HttpStatus httpStatus) {
        this.body = body;
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = System.currentTimeMillis();
        this.error = false;
    }

    public RestResponse(String message, HttpStatus httpStatus) {
        this.body = null;
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = System.currentTimeMillis();
        this.error = true;
    }

    public RestResponse(T errorMap, HttpStatus httpStatus) {
        this.body = errorMap;
        this.httpStatus = httpStatus;
        this.timestamp = System.currentTimeMillis();
        this.error = true;
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

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
