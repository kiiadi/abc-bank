package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
    //remove 'private' to moke transaction time in unit test.
    static volatile DateProvider instance = null;

    public static DateProvider getInstance() {
        if (instance == null) {
        	synchronized (DateProvider.class) {
                if (instance == null) {
                    instance = new DateProvider();
                }
			}
        }
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
}
