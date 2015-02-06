package com.abc.utils;

import java.time.Instant;
import java.util.Date;

public class DateProvider {
	
	/* Not required anymore.
	private DateProvider() {}
	
	private static class DateProviderHolder {
	    private static final DateProvider INSTANCE = new DateProvider();
	}
	  
    public static DateProvider getInstance() {
        return DateProviderHolder.INSTANCE;
    }
	*/
	
    public static Date now() {
    	return Date.from(Instant.now());
    }
}
