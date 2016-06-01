package com.abc.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * This class contains various utility functions 
 * @author santosh
 *
 */
public class Utils {
    private static Utils instance = null;

    public static Utils getInstance() {
        if (instance == null)
            instance = new Utils();
        return instance;
    }

    public Date dateNow() {
        return Calendar.getInstance().getTime();
    }

	public long daysBetween(long startTimeMs, long stopTimeMs) {
		long days = 0;
		if(stopTimeMs > startTimeMs){
			days = (stopTimeMs - startTimeMs) / (1000*60*60*24);
		}
		return days;
	}
	
	public double roundDouble(double value){
		return roundDouble(value, 2);		// default scale of 2
	}

	public double roundDouble(double value, int scale){
		return BigDecimal.valueOf(value).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public Date getPreviousDate(int daysBefore){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -daysBefore);
        return cal.getTime();
    }
}
