package com.ASSESSMENT.Meeting.config.customExeption;
/**
 * This class is responsible for creating a custom exception for the DtoNotAutorizate
 */
public class DtoNotAutorizate extends RuntimeException{
    public DtoNotAutorizate(String message) {
        super(message);
    }
}
