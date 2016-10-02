package com.abc;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateProvider {
    private static DateProvider instance = new DateProvider();

    public static DateProvider getInstance() {
        
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
    
    public static int days(Date date1, Date date2) {
		
		if(date1 == null || date2 == null || date1.after(date2)) {
			
			return 0;
		}
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date1);
		
		LocalDate d1 = LocalDate.of(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
		c.setTime(date2);
		LocalDate d2 = LocalDate.of(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
		
		return (int)ChronoUnit.DAYS.between(d1, d2);
	}
    
}
