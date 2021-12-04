package com.mayankmadhav.demo.app.mobileappws.exceptions.handler;

import com.mayankmadhav.demo.app.mobileappws.exceptions.EntityAlreadyExistsException;
import com.mayankmadhav.demo.app.mobileappws.exceptions.EntityNotFoundException;
import com.mayankmadhav.demo.app.mobileappws.exceptions.UserAuthenticationException;
import com.mayankmadhav.demo.app.mobileappws.utils.RestResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<RestResponse<Object>> handleNotFound(EntityNotFoundException ex, WebRequest req) {
        RestResponse<Object> response = new RestResponse<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<RestResponse<Object>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    protected ResponseEntity<RestResponse<Object>> handleNotAlreadyExists(EntityAlreadyExistsException ex,
                                                                          WebRequest req) {
        RestResponse<Object> response = new RestResponse<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<RestResponse<Object>>(response, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        Map<String, String> errorMap = ex.getBindingResult().getAllErrors().stream()
                .collect(Collectors.toMap(error -> ((FieldError) error).getField(), error -> ((FieldError) error).getDefaultMessage()));
        RestResponse<Object> response = new RestResponse<Object>(errorMap, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAuthenticationException.class)
    public ResponseEntity<Object> handleUnauthorized(UserAuthenticationException ex, HttpServletResponse res) {
        RestResponse<Object> response = new RestResponse<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<Object>(response, HttpStatus.UNAUTHORIZED);
    }

}
