package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
    
    /* Eager initialization of singleton. Better thread-safe idiom. */
    private static DateProvider instance = new DateProvider();

    /* Private constructor to prevent instantiation */
    private DateProvider() { }
	
    /* Avoid lazy initialization & concurrency issues */
    public static DateProvider getInstance() {
         return instance;
    }

    /* Method name modified to convey its intent */
    public Date currentTime() {
        return Calendar.getInstance().getTime();
    }
}
