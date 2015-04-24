package com.abc;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class DateProvider {
	private static volatile DateProvider instance = null;
	private static ReentrantLock instanceLock = new ReentrantLock();

	private DateProvider() {

	}

	public static DateProvider getInstance() {
		if (instance == null)
			try {
				instanceLock.lock();
				if (instance == null) {
					instance = new DateProvider();
				}
			} finally {
				instanceLock.unlock();
			}
		return instance;
	}

	public Date now() {
		return Calendar.getInstance().getTime();
	}
}
