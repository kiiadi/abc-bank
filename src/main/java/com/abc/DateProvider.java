package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
    private static DateProvider instance = null;

    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
    
    /**
     * Number of days between two dates.
     * TODO: need to change based on real world definition of days. 
     * @param fromDate
     * @param toDate
     * @return
     */
    public int getAgeInDays(Date fromDate, Date toDate) {
    	return (int)(( toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24) );
    }
}
