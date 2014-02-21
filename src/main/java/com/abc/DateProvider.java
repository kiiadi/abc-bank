package com.abc;

import java.util.Calendar;
import java.util.Date;

/**
 * Helper class to getting the date instances
 * @author Prachi
 *
 */
public class DateProvider {
    private static DateProvider instance = null;

    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    /**
     * To get current date
     * @return date object with current date
     */
    public Date now() {
        return Calendar.getInstance().getTime();
    }
}