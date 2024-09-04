package com.example.librarybooks.error;

import java.io.Serializable;
import java.util.Date;

public class ApiErrorResponse implements Serializable {

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
