package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
    private static final DateProvider instance = new DateProvider();

    public static DateProvider getInstance() {
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
}
