package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
    private static DateProvider instance = null;
    private static final long ONE_DAY = 24*60*60*1000;
    Calendar c = Calendar.getInstance();

    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    public Date now() {
        return c.getTime();
    }

    public void setFutureDate(int days) {
        c.add(Calendar.DATE, days);
    }

    public void reset() {
        c.setTimeInMillis(System.currentTimeMillis());
    }

    public long getTimeMilliSeconds(){
        return c.getTimeInMillis();
    }
}
