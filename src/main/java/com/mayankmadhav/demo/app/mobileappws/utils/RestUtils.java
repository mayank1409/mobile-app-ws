package com.mayankmadhav.demo.app.mobileappws.utils;

import com.mayankmadhav.demo.app.mobileappws.constants.MessageConstants;
import com.mayankmadhav.demo.app.mobileappws.models.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Base64;
import java.util.UUID;

public class RestUtils {

    public static <T> ResponseEntity<RestResponse<T>> handleResponse(T data) {
        RestResponse<T> response = new RestResponse<T>(data, MessageConstants.CREATED_MESSAGE, HttpStatus.OK);
        return new ResponseEntity<RestResponse<T>>(response, HttpStatus.OK);
    }

    public static <T> ResponseEntity<RestResponse<T>> handleResponse(T data, String message) {
        RestResponse<T> response = new RestResponse<T>(data, message, HttpStatus.OK);
        return new ResponseEntity<RestResponse<T>>(response, HttpStatus.OK);
    }

    public static <T> ResponseEntity<RestResponse<T>> handleGetResponse(T data) {
        RestResponse<T> response = new RestResponse<T>(data, MessageConstants.GET_MESSAGE, HttpStatus.OK);
        return new ResponseEntity<RestResponse<T>>(response, HttpStatus.OK);
    }

    public static String encode(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public static String decode(String password) {
        return Base64.getDecoder().decode(password).toString();
    }

    public static String generateUserId() {
        return UUID.randomUUID().toString();
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static boolean checkAuthentication(UserEntity user) {
        return getAuthentication().getName().equals(user.getEmail());
    }
}
