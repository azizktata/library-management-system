package com.example.librarybooks.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleObjectNotFound(Exception  ex, WebRequest request){
        final ApiErrorResponse response = ApiErrorResponse.valueOf(HttpStatus.NOT_FOUND.value(), getPath(request), ex.getMessage(), ex.getClass().getName());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private String getPath(WebRequest request) {
        return ((ServletWebRequest) request)
                .getRequest()
                .getRequestURI();
    }
}
