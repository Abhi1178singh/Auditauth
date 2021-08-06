package com.cognizant.exception;

import com.cognizant.model.CustomErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalErrorHandler {

    @Autowired
    Environment env;

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<CustomErrorResponse> handleIdNotFoundexception(LoginFailedException ex){
         CustomErrorResponse response = new CustomErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND,
                env.getProperty("string.reason.loginfail"), ex.getMessage());
         return new ResponseEntity<CustomErrorResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<CustomErrorResponse> handleTokenNotFoundException(TokenExpiredException ex){
        CustomErrorResponse response = new CustomErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND,
                env.getProperty("string.not.valid"), ex.getMessage());
        return new ResponseEntity<CustomErrorResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleUserNotFoundexception(UsernameNotFoundException ex)
    {

       CustomErrorResponse response = new CustomErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND,
               env.getProperty("string.reason.loginfail"), ex.getMessage());

        return new ResponseEntity<CustomErrorResponse>(response,HttpStatus.NOT_FOUND);
    }

}
