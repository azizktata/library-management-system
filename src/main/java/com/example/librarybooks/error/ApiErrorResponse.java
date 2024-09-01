package com.example.librarybooks.error;

import java.util.Date;

public class ApiErrorResponse {

    private Integer status;

    private String path;

    private String message;

    private String exception;

    private Date timestamp;

    private ApiErrorResponse(Integer status, String path, String message, String exception) {
        this.status = status;
        this.path = path;
        this.message = message;
        this.exception = exception;
    }

    public ApiErrorResponse() {
    }

    public static ApiErrorResponse valueOf(Integer status, String path, String message, String exception) {
        return new ApiErrorResponse(status, path, message, exception);
    }

}
