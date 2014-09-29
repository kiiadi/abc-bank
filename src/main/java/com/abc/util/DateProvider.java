package com.abc.util;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * 
 * @author Sanju Thomas
 *
 */
public final class DateProvider {
	
    private static DateProvider instance = new DateProvider();
    
    private DateProvider(){}

    public static DateProvider getInstance() {
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
    
    public int daysBetween(final Date startingDate, final Date endingDate){
    	return Days.daysBetween(new DateTime(startingDate), new DateTime(endingDate)).getDays();
    }
}
