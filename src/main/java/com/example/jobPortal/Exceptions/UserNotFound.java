package com.example.jobPortal.Exceptions;

public class UserNotFound extends RuntimeException {
    String message;

    public UserNotFound(String message) {
        super(message);
        this.message=message;
    }
}
