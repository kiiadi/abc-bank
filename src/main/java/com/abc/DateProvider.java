package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
    private Calendar calendar = Calendar.getInstance();

    private DateProvider(){}

    private static class LazyHolder {
        private static final DateProvider INSTANCE = new DateProvider();
    }

    public static DateProvider getInstance() {
        return LazyHolder.INSTANCE;
    }


    public Date now() {
        return calendar.getTime();
    }
}
