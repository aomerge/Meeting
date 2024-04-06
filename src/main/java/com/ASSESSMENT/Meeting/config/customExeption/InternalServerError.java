package com.ASSESSMENT.Meeting.config.customExeption;

public class InternalServerError extends RuntimeException{
    public InternalServerError(String message) {
        super(message);
    }
}
