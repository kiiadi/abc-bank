package com.abc;


/*
 * 
 * Alex Lerner updates ( AlecLerner@gmail.com
 * 
 */



import java.util.Calendar;
import java.util.Date;

public class DateProvider {
    private static DateProvider instance = null;

    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    public static Date now() {
        return Calendar.getInstance().getTime();
    }
}
