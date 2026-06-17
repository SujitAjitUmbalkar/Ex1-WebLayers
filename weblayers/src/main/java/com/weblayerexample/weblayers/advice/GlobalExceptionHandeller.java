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

        /*
        ### ✅ Functions / methods used in the code

* `getBindingResult()` – retrieves the result of data binding and validation from the exception
* `getAllErrors()` – returns all validation errors present in the binding result
* `stream()` – converts the list of errors into a stream for processing
* `map()` – transforms each error object into its default message
* `getDefaultMessage()` – gets the validation message defined in the annotation
* `collect()` – collects processed stream elements into a collection
* `Collectors.toList()` – converts the stream into a `List<String>`
* `ApiError.builder()` – starts building an `ApiError` object
* `status()` – sets the HTTP status inside the `ApiError` object
* `message()` – sets the error message inside the `ApiError` object
* `build()` – creates the final `ApiError` instance
* `new ResponseEntity<>(...)` – wraps the response body with an HTTP status

---

### ✅ Steps (one line each)

Validation fails and Spring throws MethodArgumentNotValidException.
The global exception handler method is invoked automatically.
Validation details are extracted from the exception using getBindingResult().
All validation error objects are obtained using getAllErrors().
Each error is converted into a readable message using getDefaultMessage() with stream and map.
All validation messages are collected into a list.
An ApiError object is created using the builder.
HTTP status BAD_REQUEST, a generic message, and the list of sub-errors are set.
The structured error response is returned to the client using ResponseEntity.

         */
    }
}

//Added Another methid to handle validation errors in a proper, controlled, and professional way
// (steps : Validation fails and Spring throws MethodArgumentNotValidException,
// the global exception handler method is invoked automatically,
// validation details are extracted from the exception using getBindingResult(),
// all validation error objects are obtained using getAllErrors(),
// each error is converted into a readable message using getDefaultMessage() using stream and map,
// all validation messages are collected into a list, an ApiError object is created using the builder,
// HTTP status BAD_REQUEST along with a generic message and the list of sub-errors are set,
// and the structured error response is returned to the client using ResponseEntity).