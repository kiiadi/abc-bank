package com.abc.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class Utils {
	
    public static Date getNow() {
        return Calendar.getInstance().getTime();
    }
    
    public static long getDaysBetween(Date startDate, Date endDate) {
    	Date normalizedStartDate = Utils.normalizeDay(startDate);
    	Date normalizedEndDate = Utils.normalizeDay(endDate);
    	return (normalizedEndDate.getTime() - normalizedStartDate.getTime())/3600000/24;
    }
    
    public static Date normalizeDay(Date rawDate) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(rawDate);
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MILLISECOND, 0);
    	return cal.getTime();
    }
    
    public static String format(Date date, SimpleDateFormat format) {
    	return format.format(date);
    }
    
    public static SimpleDateFormat getYYYY_MM_DD_SimpleDisplayFormat() {
    	return new SimpleDateFormat(Constants.YYYY_MM_DD, Locale.US);
    }
  
    public static final String displayRoundedWithCurrency(BigDecimal amount) {
    	return NumberFormat.getCurrencyInstance(Locale.US).format(amount.setScale(2, BigDecimal.ROUND_HALF_UP));
    }
    
}
