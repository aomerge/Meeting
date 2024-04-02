package com.ASSESSMENT.Meeting.config.customExeption;

public class TokenNotAutorization  extends RuntimeException{
    public TokenNotAutorization(String message) {
        super(message);
    }

}
