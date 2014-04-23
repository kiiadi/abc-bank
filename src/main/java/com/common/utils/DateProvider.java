package com.common.utils;

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

    public Date incrementDate(Date date, int increment) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int direction = (increment < 0) ? -1 : 1;
        int numberDays = Math.abs(increment);

        int counter = 0;

        while (counter < numberDays) {
            cal.add(Calendar.DATE, direction);
            counter++;
        }

        return cal.getTime();
    }
}
