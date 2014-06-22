package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {

    private static DateProvider instance = null;
    
    // Singleton
    private DateProvider(){}
    
    public static DateProvider getInstance() {
        if (instance == null) {
            instance = new DateProvider();
        }
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
}
