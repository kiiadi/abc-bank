package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
	
	// Henry made it thread safe
    private static volatile DateProvider instance = null;
    
    private DateProvider () {
    	
    }

    public static DateProvider getInstance() {
    	// Henry made it thread safe
        if (instance == null) {
        	synchronized (DateProvider.class) {
        		if (instance == null) {
        			 instance = new DateProvider();
                }
        	}        	
        }
           
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
}
