package com.abc;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateProvider {

	// Instantiate the variable right away to guarantee single instance.
	private static DateProvider instance = new DateProvider();

	public static DateProvider getInstance() {
		return instance;
	}

	// Making the method static, as it does not need to be tied to a specific
	// instance.
	public static Date now() {
		return Calendar.getInstance().getTime();
	}

	public static Date addDays(int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		return cal.getTime();

	}
}
