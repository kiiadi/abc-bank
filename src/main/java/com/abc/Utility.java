package com.abc;

import static java.lang.Math.abs;

import java.util.Calendar;
import java.util.Date;

public class Utility {

	public static String toDollars(double d) {
		return String.format("$%,.2f", abs(d));
	}

	public static String format(int number, String word) {
		if(number < 1)
			return "No "+word+"s";
		return number + " " + (number == 1 ? word : word + "s");
	}

	public static Date now() {
		return Calendar.getInstance().getTime();
	}

}
