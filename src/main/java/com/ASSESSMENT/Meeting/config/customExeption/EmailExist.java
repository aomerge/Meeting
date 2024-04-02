package com.ASSESSMENT.Meeting.config.customExeption;
/**
 * This class is responsible for creating a custom exception for the EmailExist
 */
public class EmailExist extends RuntimeException{
    public EmailExist(String message) {
        super(message);
    }

}
