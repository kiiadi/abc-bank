package com.abc.util;

import java.util.Calendar;
import java.util.Date;

public class DefaultDateProvider implements DateProvider {
	
    private final static DefaultDateProvider instance = new DefaultDateProvider();

    public static DefaultDateProvider getInstance() {
        return instance;
    }

    @Override
    public Date now() {
        return Calendar.getInstance().getTime();
    }
}
