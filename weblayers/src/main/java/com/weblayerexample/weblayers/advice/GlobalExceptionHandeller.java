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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> HandleInternalServerError(Exception eobj)
    {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(eobj.getMessage())
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    ring always tries to find the most specific @ExceptionHandler first.
//    If no specific handler exists,
//    it falls back to a more general handler such as @ExceptionHandler(Exception.class).
//    This acts as a catch-all handler for unexpected errors.
}