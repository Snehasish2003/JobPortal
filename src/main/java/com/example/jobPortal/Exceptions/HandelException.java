package com.example.jobPortal.Exceptions;

import com.example.jobPortal.Dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandelException {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        return new Response<>(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),null);
    }

    @ExceptionHandler(value = UserNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> userNotFound(UserNotFound ex){
        return new Response<>(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),null);
    }

    @ExceptionHandler(value = JobNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> userNotFound(JobNotFound ex){
        return new Response<>(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),null);
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> userNotFound(NullPointerException ex){
        return new Response<>(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),null);
    }



}
