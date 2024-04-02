package com.ASSESSMENT.Meeting.config.Error;

import org.springframework.http.HttpStatus;

public class ApiError {
    private HttpStatus status;
    private String message;
    private int errorCode;

    public ApiError(HttpStatus status ,String message) {
        super();
        this.status = status;
        this.message = message;
        this.errorCode = status.value();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
