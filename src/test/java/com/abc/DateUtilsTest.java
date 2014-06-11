package com.abc;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link com.abc.DateUtils}
 */
public class DateUtilsTest {
    @Test
    public void testGetDaysBetween_WhenOneDay() throws Exception {
        Date date1 = DateUtils.getDate(2014, 1, 1);
        Date date2 = DateUtils.getDate(2014, 1, 2);

        assertEquals(1, DateUtils.getDaysBetween(date1, date2));
        assertEquals(1, DateUtils.getDaysBetween(date2, date1));
    }

    @Test
    public void testGetDaysBetween_WhenOneAndAHalfDays() throws Exception {
        Date date1 = getDateTime(2014, 1, 1, 0, 0, 0);
        Date date2 = getDateTime(2014, 1, 2, 12, 0, 0);

        assertEquals(1, DateUtils.getDaysBetween(date1, date2));
        assertEquals(1, DateUtils.getDaysBetween(date2, date1));
    }

    @Test
    public void testGetDaysBetween_WhenLessThanOneDay() throws Exception {
        Date date1 = getDateTime(2014, 1, 1, 0, 0, 0);
        Date date2 = getDateTime(2014, 1, 1, 23, 59, 59);

        assertEquals(0, DateUtils.getDaysBetween(date1, date2));
        assertEquals(0, DateUtils.getDaysBetween(date2, date1));
    }

    @Test
    public void testGetDaysBetween_WhenLongPeriod() throws Exception {
        Date date1 = DateUtils.getDate(2014, 1, 1);
        Date date2 = DateUtils.getDate(2024, 1, 1);

        assertEquals(3652, DateUtils.getDaysBetween(date1, date2));
        assertEquals(3652, DateUtils.getDaysBetween(date2, date1));
    }


    @Test
    public void testAddOneDay() throws Exception {
        Date date = DateUtils.getDate(2014, 1, 1);
        Date nextDay = DateUtils.getDate(2014, 1, 2);

        assertEquals(nextDay, DateUtils.addOneDay(date));
    }

    private static Date getDateTime(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month, dayOfMonth, hour, minute, second);
        return calendar.getTime();
    }
}
