package com.abc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateProvider {
	private static DateProvider instance = null;
	private final SimpleDateFormat formatterYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	private final static long MILLISECONDS_IN_A_DAY = 24 * 60 * 60 * 1000;
	private Calendar calendar = Calendar.getInstance();

	private DateProvider() {
	}

	public static DateProvider getInstance() {
		if (instance == null)
			instance = new DateProvider();
		return instance;
	}

	public Date dateFromString(String inputYMD) {
		Date d = null;
		try {
			d = formatterYYYYMMDD.parse(inputYMD);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return isolateDateComponents(d);
	}

	public Date isolateDateComponents(Date inDate) {
		calendar.setTime(inDate);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public Date getDateByYMD(int year, int month, int day) {
		if (year < 1970 || year > 2999 || month < 1 || month > 12 || day < 1 || day > 31) {
			throw new IllegalArgumentException("invalid date component");
		}
		calendar.set(year, month - 1, day, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public Date addDays(Date date, int numberOfDays) {
		long newTime = date.getTime() + (numberOfDays * MILLISECONDS_IN_A_DAY);
		return new Date(newTime);
	}

	public long calendarDaysDifference(Date d1, Date d2) {
		long rv = (d2.getTime() - d1.getTime()) / MILLISECONDS_IN_A_DAY;
		return rv;
	}

	public Date now() {
		return Calendar.getInstance().getTime();
	}
}
