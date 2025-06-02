package com.example.jobPortal.Exceptions;

public class JobNotFound extends RuntimeException {
   public String message;
    public JobNotFound(String message) {
        super(message);
        this.message=message;
    }
}
