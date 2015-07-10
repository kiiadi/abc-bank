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

    public synchronized Date now() {
        return Calendar.getInstance().getTime();
    }
    
    public synchronized static Date daysAgo(int count) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, (count * -1));
        return cal.getTime();
    }
}
