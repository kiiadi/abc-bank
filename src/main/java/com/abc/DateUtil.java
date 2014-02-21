package com.abc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static DateUtil instance = null;
	private final SimpleDateFormat formatterYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	private final static long MILLISECONDS_IN_A_DAY = 24 * 60 * 60 * 1000;
	private Calendar calendar = Calendar.getInstance();

	// private constructor
	private DateUtil() {
	}

	public static DateUtil getInstance() {
		if (instance == null)
			instance = new DateUtil();
		return instance;
	}

	/**
	 * Zero out the time component of a given Date object
	 * leaving only the date components (year, month, date)
	 * @param inDate input Date object
	 * @return output Date object having its time component zeroed out
	 */
	public Date isolateDateComponents(Date inDate) {
		calendar.setTime(inDate);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Create a date, given a string in YYYYMMDD format
	 * @param inputYMD input YYYYMMDD string
	 * @return Date object having no Time component
	 */
	public Date dateFromYYYYMMDDString(String inputYMD) {
		Date d;
		try {
			d = formatterYYYYMMDD.parse(inputYMD);
		} catch (ParseException e) {
			System.out.println("***** error parsing input string:" + inputYMD);
			return null;
		}
		return isolateDateComponents(d);
	}

	/**
	 * Create a Date object given year, month, and day of month
	 *
	 * @param year input year (1970 to 2999)
	 * @param month input month (1 to 12)
	 * @param day (1 to 31)
	 * @return a Date object
	 */
	public Date dateFromYMD(int year, int month, int day) {
		if (year < 1970 || year > 2999 || month < 1 || month > 12 || day < 1 || day > 31) {
			throw new IllegalArgumentException("invalid date component");
		}
		calendar.set(year, month - 1, day, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Add N days to a given Date
	 * @param date input Date object
	 * @param numberOfDays number of days to add to the input date
	 * @return resulting Date object
	 */
	public Date addDays(Date date, int numberOfDays) {
		long newTime = date.getTime() + (numberOfDays * MILLISECONDS_IN_A_DAY);
		return new Date(newTime);
	}


	/**
	 * Calculate difference in days between two dates
	 * @param dateFrom 'from' date
	 * @param dateTo 'to' date
	 * @return number of calendar days between two input dates
	 */
	public long calendarDaysDifference(Date dateFrom, Date dateTo) {
		return (dateTo.getTime() - dateFrom.getTime()) / MILLISECONDS_IN_A_DAY;
	}

	public Date now() {
		// todo: is this more efficient than "new Date()" ?
		return Calendar.getInstance().getTime();
	}
}
