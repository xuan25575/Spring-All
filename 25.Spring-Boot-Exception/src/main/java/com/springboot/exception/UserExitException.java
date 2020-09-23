package com.springboot.exception;

public class UserExitException extends RuntimeException {



    public UserExitException() {
        super();
    }

    public UserExitException(String message) {
        super(message);
    }
}
