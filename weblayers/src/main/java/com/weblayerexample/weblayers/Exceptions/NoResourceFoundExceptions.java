package com.weblayerexample.weblayers.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoResourceFoundExceptions extends RuntimeException
{
    public NoResourceFoundExceptions(String message)
    {
        super(message);
    }
}
/*
        This custom exception is important because it represents application-specific errors clearly,
        improves code readability, avoids using generic Java exceptions,
        helps in sending meaningful error messages to clients,
        enables centralized handling through global exception handlers,
         keeps the project structure clean, and follows industry-standard Spring Boot best practices.
 */