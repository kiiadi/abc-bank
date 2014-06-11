package com.abc;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Utilities for date calculations.
 */
public final class DateUtils {

    public static final int MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24;

    private DateUtils() {}

    /**
     * Gets days from the first date and second date.
     * @param fromDate the start date
     * @param toDate the end date
     * @return the count
     */
    public static int getDaysBetween(Date fromDate, Date toDate) {
        long differenceInMillis = Math.abs(toDate.getTime() - fromDate.getTime());
        return (int) Math.floor(differenceInMillis / MILLISECONDS_IN_DAY);
    }

    /**
     * Creates a date.
     * @param year the year
     * @param month the month
     * @param dayOfMonth the day
     * @return the date
     */
    public static Date getDate(int year, int month, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month, dayOfMonth, 0, 0, 0);
        calendar.set(GregorianCalendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * Returns a value that is a day later than the given date.
     * @param theDate the date
     * @return the date
     */
    public static Date addOneDay(Date theDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTime(theDate);
        calendar.add(GregorianCalendar.DAY_OF_YEAR, 1);

        return calendar.getTime();
    }
}
