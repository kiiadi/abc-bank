package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
    private final static DateProvider instance = new DateProvider();
    private final static long DAY_IN_MS =  24 * 60 * 60 * 1000;

    public static DateProvider getInstance() {
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
    
    public long dateDiff(Date from, Date to) {
    	to = (to == null)?DateProvider.getInstance().now():to;
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(from);
		calendar2.setTime(to);
		long dateDiff = (calendar2.getTimeInMillis() - calendar1.getTimeInMillis()) / DAY_IN_MS;
		return dateDiff;
    }
}
