package com.cognizant.exception;

import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@NoArgsConstructor
public class LoginFailedException extends RuntimeException {

    public LoginFailedException(String message){
        super(message);
    }

    public LoginFailedException(Throwable cause){
        super(cause);
    }

    public LoginFailedException(String message, Throwable cause){
        super(message, cause);
    }

    public LoginFailedException(String message, Throwable cause, boolean enableSuppression,
                                boolean writeableStackTrace){
        super(message, cause, enableSuppression, writeableStackTrace);
    }
}
