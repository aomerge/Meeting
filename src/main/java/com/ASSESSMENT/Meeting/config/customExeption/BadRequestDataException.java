package com.ASSESSMENT.Meeting.config.customExeption;

public class BadRequestDataException extends RuntimeException {
    public BadRequestDataException(String message) {
        super(message);
    }
}
