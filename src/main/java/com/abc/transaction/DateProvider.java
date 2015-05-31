package com.abc.transaction;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {

    private static DateProvider instance = new DateProvider();

    public static DateProvider getInstance() {
        return instance;
    }

    public static void setInstance(DateProvider dateProvider) {
        instance = dateProvider;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
}
