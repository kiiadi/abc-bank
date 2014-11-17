package com.abc;

import java.util.Calendar;
import java.util.Date;

/**
 * Utility class to get current time
 * @author Rakesh
 *
 */
public class DateProvider {
	/**
	 * Date provider
	 */
	private static DateProvider instance = null;

	/**
	 * Static method to create an instance
	 * @return Instance
	 */
	public static DateProvider getInstance() {
		if (instance == null)
			instance = new DateProvider();
		return instance;
	}

	/**
	 * Get the current time
	 * @return Current time
	 */
	public Date now() {
		return Calendar.getInstance().getTime();
	}
}
