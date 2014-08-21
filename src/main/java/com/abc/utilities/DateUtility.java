package com.abc.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 * User: bradharper
 * Date: 8/18/14
 * Time: 11:12 PM
 * <p/>
 * Convenience methods for date creation, manipulation and consideration.
 */
public class DateUtility {

    public static final String DATE_FORMAT = "MM/dd/yyyy";

    public static enum TimeZones {
        EST(TimeZone.getTimeZone("America/New York")),
        CST(TimeZone.getTimeZone("America/Chicago")),
        MST(TimeZone.getTimeZone("America/Denver")),
        PST(TimeZone.getTimeZone("America/Los_Angeles"));

        TimeZones(final TimeZone timeZone) {
            this.timeZone = timeZone;
        }

        private final TimeZone timeZone;

        public TimeZone getValue() {
            return timeZone;
        }
    }

    public static enum CronUnit {

        MILLIS("MILLIS"),
        SECONDS("SECONDS"),
        MINUTES("MINUTES"),
        HOURS("HOURS"),
        DAYS("DAYS"),
        WEEKS("WEEKS"),
        MONTHS("MONTHS"),
        YEARS("YEARS");

        /**
         * @param value
         */
        CronUnit(final String value) {
            this.value = value;
        }

        /**
         * value to be retrieved according to the enum type
         */
        private final String value;

        /**
         * @return String
         */
        public String getValue() {
            return value;
        }
    }

    public static final int DEFAULT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    /**
     * @return Date
     */
    public static Date now() {
        return new Date();
    }

    /**
     * @param dayOfMonth
     * @return Date
     */
    public static Date january(final int dayOfMonth) {
        return january(dayOfMonth, DEFAULT_YEAR);
    }

    /**
     * @param dayOfMonth
     * @param year
     * @return Date
     */
    public static Date january(final int dayOfMonth, final int year) {
        return createDate(dayOfMonth, Calendar.JANUARY, year);
    }

    /**
     * @param dayOfMonth
     * @return Date
     */
    public static Date february(final int dayOfMonth) {
        return february(dayOfMonth, DEFAULT_YEAR);
    }

    /**
     * @param dayOfMonth
     * @param year
     * @return Date
     */
    public static Date february(final int dayOfMonth, final int year) {
        return createDate(dayOfMonth, Calendar.FEBRUARY, year);
    }

    /**
     * @param dayOfMonth
     * @return Date
     */
    public static Date march(final int dayOfMonth) {
        return march(dayOfMonth, DEFAULT_YEAR);
    }

    /**
     * @param dayOfMonth
     * @param year
     * @return Date
     */
    public static Date march(final int dayOfMonth, final int year) {
        return createDate(dayOfMonth, Calendar.MARCH, year);
    }

    /**
     * @param dayOfMonth
     * @return Date
     */
    public static Date april(final int dayOfMonth) {
        return april(dayOfMonth, DEFAULT_YEAR);
    }

    /**
     * @param dayOfMonth
     * @param year
     * @return Date
     */
    public static Date april(final int dayOfMonth, final int year) {
        return createDate(dayOfMonth, Calendar.APRIL, year);
    }

    /**
     * @param dayOfMonth
     * @return Date
     */
    public static Date may(final int dayOfMonth) {
        return may(dayOfMonth, DEFAULT_YEAR);
    }

    /**
     * @param dayOfMonth
     * @param year
     * @return Date
     */
    public static Date may(final int dayOfMonth, final int year) {
        return createDate(dayOfMonth, Calendar.MAY, year);
    }

    /**
     * @param dayOfMonth
     * @return Date
     */
    public static Date june(final int dayOfMonth) {
        return june(dayOfMonth, DEFAULT_YEAR);
    }

    /**
     * @param dayOfMonth
     * @param year
     * @return Date
     */
    public static Date june(final int dayOfMonth, final int year) {
        return createDate(dayOfMonth, Calendar.JUNE, year);
    }

    /**
     * @param dayOfMonth
     * @return Date
     */
    public static Date july(final int dayOfMonth) {
        return july(dayOfMonth, DEFAULT_YEAR);
    }

    /**
     * @param dayOfMonth
     * @param year
     * @return Date
     */
    public static Date july(final int dayOfMonth, final int year) {
        return createDate(dayOfMonth, Calendar.JULY, year);
    }

    /**
     * @param dayOfMonth
     * @return Date
     */
    public static Date august(final int dayOfMonth) {
        return august(dayOfMonth, DEFAULT_YEAR);
    }

    /**
     * @param dayOfMonth
     * @param year
     * @return Date
     */
    public static Date august(final int dayOfMonth, final int year) {
        return createDate(dayOfMonth, Calendar.AUGUST, year);
    }

    /**
     * @param dayOfMonth
     * @return Date
     */
    public static Date september(final int dayOfMonth) {
        return september(dayOfMonth, DEFAULT_YEAR);
    }

    /**
     * @param dayOfMonth
     * @param year
     * @return Date
     */
    public static Date september(final int dayOfMonth, final int year) {
        return createDate(dayOfMonth, Calendar.SEPTEMBER, year);
    }

    /**
     * @param dayOfMonth
     * @return Date
     */
    public static Date october(final int dayOfMonth) {
        return october(dayOfMonth, DEFAULT_YEAR);
    }

    public static Date october(final int dayOfMonth, final int year) {
        return createDate(dayOfMonth, Calendar.OCTOBER, year);
    }

    /**
     * @param dayOfMonth
     * @return Date
     */
    public static Date november(final int dayOfMonth) {
        return november(dayOfMonth, DEFAULT_YEAR);
    }

    /**
     * @param dayOfMonth
     * @param year
     * @return Date
     */
    public static Date november(final int dayOfMonth, final int year) {
        return createDate(dayOfMonth, Calendar.NOVEMBER, year);
    }

    /**
     * @param dayOfMonth
     * @return Date
     */
    public static Date december(final int dayOfMonth) {
        return december(dayOfMonth, DEFAULT_YEAR);
    }

    /**
     * @param dayOfMonth
     * @param year
     * @return Date
     */
    public static Date december(final int dayOfMonth, final int year) {
        return createDate(dayOfMonth, Calendar.DECEMBER, year);
    }

    /**
     * @return Date today's date
     */
    public static Date today() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.clear(Calendar.MILLISECOND);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MINUTE);
        cal.set(Calendar.HOUR_OF_DAY, 0);

        return cal.getTime();
    }

    /**
     * Returns date with the time part shifted based on the minutes passed.
     *
     * @param minutes
     * @return Date
     */
    public static Date minutesAgo(final int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.MINUTE, -minutes);
        return cal.getTime();
    }

    /**
     * Returns date with the time part shifted based on the minutes passed.
     *
     * @param minutes
     * @return Date
     */
    public static Date minutesAhead(final int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    /**
     * Returns date with the time part shifted based on the minutes passed.
     *
     * @param minutes
     * @return Date
     */
    public static Date minutesApart(final int minutes) {
        if (minutes > 0) {
            return minutesAhead(minutes);
        } else {
            return minutesAgo(-minutes);
        }
    }

    /**
     * Returns date with the time part shifted based on the hours passed.
     *
     * @param hours
     * @return Date
     */
    public static Date hoursAgo(final int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.HOUR_OF_DAY, -hours);
        return cal.getTime();
    }

    /**
     * Returns date with the time part shifted based on the hours passed.
     *
     * @param hours
     * @return Date
     */
    public static Date hoursAhead(final int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }

    /**
     * Returns date with the time part shifted based on the hours passed.
     *
     * @param hours
     * @return Date
     */
    public static Date hoursApart(final int hours) {
        if (hours > 0) {
            return hoursAhead(hours);
        } else {
            return hoursAgo(-hours);
        }
    }

    /**
     * Returns date with the date shifted based on the days passed.
     *
     * @param days
     * @return Date
     */
    public static Date daysAgo(final int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }

    /**
     * Returns date with the date shifted based on the days passed.
     *
     * @param days
     * @return Date
     */
    public static Date daysAgoSquare(final int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.DATE, -days);

        cal.clear(Calendar.MILLISECOND);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MINUTE);
        cal.set(Calendar.HOUR_OF_DAY, 0);

        return cal.getTime();
    }

    /**
     * Returns date with the date shifted based on the days passed.
     *
     * @param days
     * @return Date
     */
    public static Date daysAhead(final int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * Returns date with the date shifted based on the days passed.
     *
     * @param days
     * @return Date
     */
    public static Date daysAheadSquare(final int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.DATE, days);

        cal.clear(Calendar.MILLISECOND);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MINUTE);
        cal.set(Calendar.HOUR_OF_DAY, 0);

        return cal.getTime();
    }

    /**
     * Returns date with the date shifted based on the days passed.
     *
     * @param days
     * @return
     */
    public static Date daysApart(final int days) {
        if (days > 0) {
            return daysAhead(days);
        } else {
            return daysAgo(-days);
        }
    }

    /**
     * Returns date with the date shifted based on the days passed.
     *
     * @param days
     * @return Date
     */
    public static Date daysApartSquare(final int days) {
        if (days > 0) {
            return daysAheadSquare(days);
        } else {
            return daysAgoSquare(-days);
        }
    }

    /**
     * Returns date with the date shifted based on the weeks passed.
     *
     * @param weeks
     * @return Date
     */
    public static Date weeksAgo(final int weeks) {
        return daysAgo(weeks * 7);
    }

    /**
     * Returns date with the date shifted based on the weeks passed.
     *
     * @param weeks
     * @return Date
     */
    public static Date weeksAgoSquare(final int weeks) {
        return daysAgoSquare(weeks * 7);
    }

    /**
     * Returns date with the date shifted based on the weeks passed.
     *
     * @param weeks
     * @return Date
     */
    public static Date weeksAhead(final int weeks) {
        return daysAhead(weeks * 7);
    }

    /**
     * Returns date with the date shifted based on the weeks passed.
     *
     * @param weeks
     * @return Date
     */
    public static Date weeksAheadSquare(final int weeks) {
        return daysAheadSquare(weeks * 7);
    }

    /**
     * Returns date with the date shifted based on the weeks passed.
     *
     * @param weeks
     * @return Date
     */
    public static Date weeksApart(final int weeks) {
        if (weeks > 0) {
            return daysAhead(weeks);
        } else {
            return daysAgo(-weeks);
        }
    }

    /**
     * Returns date with the date shifted based on the weeks passed.
     *
     * @param weeks
     * @return Date
     */
    public static Date weeksApartSquare(final int weeks) {
        if (weeks > 0) {
            return daysAheadSquare(weeks);
        } else {
            return daysAgoSquare(-weeks);
        }
    }

    /**
     * Returns date with the date shifted based on the months passed.
     *
     * @param months
     * @return Date
     */
    public static Date monthsAgo(final int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.MONTH, -months);
        return cal.getTime();
    }

    /**
     * Returns date with the date shifted based on the months passed.
     *
     * @param months
     * @return Date
     */
    public static Date monthsAgoSquare(final int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.MONTH, -months);
        cal.clear(Calendar.MILLISECOND);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MINUTE);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }

    /**
     * Returns date with the date shifted based on the months passed.
     *
     * @param months
     * @return Date
     */
    public static Date monthsAhead(final int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * Returns date with the date shifted based on the months passed.
     *
     * @param months
     * @return Date
     */
    public static Date monthsAheadSquare(final int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.MONTH, months);
        cal.clear(Calendar.MILLISECOND);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MINUTE);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }

    /**
     * Returns date with the date shifted based on the months passed.
     *
     * @param months
     * @return Date
     */
    public static Date monthsApart(final int months) {
        if (months > 0) {
            return monthsAhead(months);
        } else {
            return monthsAgo(months);
        }
    }

    /**
     * Returns date with the date shifted based on the months passed.
     *
     * @param months
     * @return Date
     */
    public static Date monthsApartSquare(final int months) {
        if (months > 0) {
            return monthsAheadSquare(months);
        } else {
            return monthsAgoSquare(-months);
        }
    }

    /**
     * Returns date with the date shifted based on the years passed.
     *
     * @param years
     * @return Date
     */
    public static Date yearsAgo(final int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.YEAR, -years);
        return cal.getTime();
    }

    /**
     * Returns date with the date shifted based on the years passed.
     *
     * @param years
     * @return Date
     */
    public static Date yearsAgoSquare(final int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.YEAR, -years);
        cal.clear(Calendar.MILLISECOND);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MINUTE);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }

    /**
     * Returns date with the date shifted based on the years passed.
     *
     * @param years
     * @return Date
     */
    public static Date yearsAhead(final int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    /**
     * Returns date with the date shifted based on the years passed.
     *
     * @param years
     * @return Date
     */
    public static Date yearsAheadSquare(final int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.YEAR, years);
        cal.clear(Calendar.MILLISECOND);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MINUTE);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }

    /**
     * Returns date with the date shifted based on the years passed.
     *
     * @param years
     * @return Date
     */
    public static Date yearsApart(final int years) {
        if (years > 0) {
            return yearsAhead(years);
        } else {
            return yearsAgo(years);
        }
    }

    /**
     * Returns date with the date shifted based on the years passed.
     *
     * @param years
     * @return Date
     */
    public static Date yearsApartSquare(final int years) {
        if (years > 0) {
            return yearsAheadSquare(years);
        } else {
            return yearsAgoSquare(-years);
        }
    }

    /**
     * Returns date with the date/time shifted based on the cronUnit passed.
     *
     * @param cronUnit
     * @param units
     * @return Date
     */
    public static Date unitsApart(final CronUnit cronUnit, final int units) {
        if (cronUnit != null) {
            switch (cronUnit) {
                case MILLIS:
                case SECONDS:
                    throw new RuntimeException("operation not supported");

                case MINUTES:
                    return minutesApart(units);

                case HOURS:
                    return hoursApart(units);

                case DAYS:
                    return daysApartSquare(units);

                case WEEKS:
                    return weeksApartSquare(units);

                case MONTHS:
                    return monthsApartSquare(units);

                case YEARS:
                    return yearsApartSquare(units);

            }
        }
        return null;
    }

    /**
     * Creates date based on GregorianCalendar.
     *
     * @param dayOfMonth
     * @param month
     * @param year
     * @return Date
     */
    public static Date createDate(final int dayOfMonth, final int month, final int year) {
        return new GregorianCalendar(year, month, dayOfMonth).getTime();
    }

    /**
     * Returns the difference in days.
     *
     * @param one
     * @param two
     * @return int
     */
    public static int daysBetweenDates(final Date one, final Date two) {
        return (int) ((two.getTime() - one.getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * @param toTest
     * @param startDate
     * @param endDate
     * @return boolean
     */
    public static boolean isWithinRange(final Date toTest, final Date startDate, final Date endDate) {
        return !(toTest.before(startDate) || toTest.after(endDate));
    }

    /**
     * Gives a date a trim.  Sets the provided date to precisely midnight, while preserving day, month, and year.
     *
     * @param toShave
     * @return the provided date trimmed to midnight.
     */
    public static Date shave(final Date toShave) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(toShave);
        cal.clear(Calendar.MILLISECOND);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MINUTE);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }

    /**
     * Gives a date a trim or boost to noon.  Sets the provided date to precisely noon, while preserving day, month, and year.
     *
     * @param toNoon
     * @return the provided date trimmed to noon.
     */
    public static Date noon(final Date toNoon) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(toNoon);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        return cal.getTime();
    }

    /**
     * Gives a date a boost.  Sets the provided date to precisely 1 millisecond before the next day, while preserving
     * day, month, and year.
     *
     * @param toExtend
     * @return
     */
    public static Date extend(final Date toExtend) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(toExtend);
        cal.set(Calendar.MILLISECOND, 999);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        return cal.getTime();
    }


    /**
     * Returns the day of year portion of the provided date.
     * @param date
     * @return
     */
    public static int getDayOfYear(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean sameDay(Date date1, Date date2){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(date1).equals(fmt.format(date2));
    }

}
