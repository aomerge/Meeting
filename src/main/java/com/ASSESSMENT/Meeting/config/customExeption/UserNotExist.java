package com.ASSESSMENT.Meeting.config.customExeption;
/** this class is used to create a custom exception when the user does not exist
 * @param message is the message that will be displayed when the exception is thrown
 * */
public class UserNotExist extends RuntimeException{
    public UserNotExist(String message) {
        super(message);
    }
}
