package com.weblayerexample.weblayers.advice;

import com.weblayerexample.weblayers.Exceptions.NoResourceFoundExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> HandleInputValidation(MethodArgumentNotValidException eobj)
    {
        List<String> errors = eobj
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error ->error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
//                        .message(errors.toString())
                .message("Input Validation Failed")
                .subErrors(errors)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

    }
}