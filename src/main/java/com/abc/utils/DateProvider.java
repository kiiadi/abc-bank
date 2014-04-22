package com.abc.utils;

import java.util.Calendar;
import java.util.Date;

public final class DateProvider {
    private static DateProvider instance = null;
    
    private DateProvider() {
    	// Exists only to prevent instantiation
    }

    public static DateProvider getInstance() {
        if (instance == null) {
            instance = new DateProvider();
        }
        
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
    
    public Date getDate(int addNumOfDays) {
    	Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, addNumOfDays);
		return calendar.getTime();
    }
}

