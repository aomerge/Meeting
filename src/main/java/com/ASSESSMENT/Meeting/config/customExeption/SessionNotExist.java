package com.ASSESSMENT.Meeting.config.customExeption;

public class SessionNotExist  extends RuntimeException{
    public SessionNotExist(String message) {
        super(message);
    }
}
