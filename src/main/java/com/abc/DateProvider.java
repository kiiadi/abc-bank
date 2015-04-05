package com.abc;

import org.joda.time.DateTime;
import org.joda.time.Days;


public class DateProvider {
	public static DateTime now() {
		return new DateTime();
	}
	public static int getDiffInDays(DateTime date) {
		return Days.daysBetween( date ,new DateTime()).getDays();
	}
}
