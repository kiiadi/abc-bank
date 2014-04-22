package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
	
	private DateProvider() {};
	
    private static DateProvider instance = null;

    public static synchronized DateProvider getInstance() {
    	if (instance == null) {
            instance = new DateProvider();
        }
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
}
