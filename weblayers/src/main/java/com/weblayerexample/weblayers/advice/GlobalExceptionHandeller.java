package com.weblayerexample.weblayers.advice;

import com.weblayerexample.weblayers.Exceptions.NoResourceFoundExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.NoSuchElementException;
@RestControllerAdvice
public class GlobalExceptionHandeller
{
    @ExceptionHandler(NoSuchElementException.class)         // globally
    public ResponseEntity<ApiError> HandleResourceNotFound(NoSuchElementException e)
    {
        ApiError apiError = ApiError.builder().status(HttpStatus.NOT_FOUND).message("Resource Not Found").build();

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoResourceFoundExceptions.class)
    public ResponseEntity<ApiError> handleResourceNotFound(
            NoResourceFoundExceptions ex)
    {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}