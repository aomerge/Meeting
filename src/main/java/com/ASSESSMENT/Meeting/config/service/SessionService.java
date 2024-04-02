package com.ASSESSMENT.Meeting.config.service;

import java.util.Date;

public class SessionService {
    /**
     * This method validates the date
     * @param date
     * @param message
     */
    public void ValidationDate(Date date, String message) {
        PvtValidationData(date, message );
    }
    /**
     * This method validates the float number
     * @param number
     * @param message
     */
    public void ValidationFloat(Float number, String message) {
        PvtValidationFloat(number, message);
    }

    private void PvtValidationData(Date date, String message) {
        if (date == null){
            throw new IllegalArgumentException(message);
        }
    }
    private void PvtValidationFloat(Float number, String message) {
        if (number == null){
            throw new IllegalArgumentException(message);
        }
    }


}
