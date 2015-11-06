package com.abc;

import java.util.Calendar;
import java.util.Date;

/*
 * Change this to SingleTon as we need to keep only 1 instance
 */
public class DateProvider {
    private static DateProvider instance = null;
    
    private DateProvider()
    {
    }
    public static DateProvider getInstance() {
    	
        if (instance != null) {
        	return instance;
        }
        synchronized (DateProvider.class) {
        	if(instance == null)
                instance = new DateProvider();
		}
        return instance;
    }
    public Date now() {
        return Calendar.getInstance().getTime();
    }
}
