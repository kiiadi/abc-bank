package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
	// Eager creation of Signleton. 
	// Simpler and safer than Double Checked Locking with Volatile variable.
    private static DateProvider instance = new DateProvider();

    public static DateProvider getInstance() {
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }

    public Date lastYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        return calendar.getTime();
    }
}
