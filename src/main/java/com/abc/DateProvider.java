package com.abc;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Date;

public class DateProvider {
    private static DateProvider instance = new DateProvider();

    public static DateProvider getInstance() {
        return instance;
    }

    public Date now() {
        return DateTime.now(DateTimeZone.UTC).toDate();
    }
}
