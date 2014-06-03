package com.abc;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;


/**
 * The Class DateProvider.   This is implemented with a thread-safe singleton.
 */
public class DateProvider {
    
    /** The instance. */
    private static volatile DateProvider instance = null;
    private static SimpleDateFormat sdf = null;
    private static SimpleDateFormat sdtf = null;
     
    
    /**
     * Instantiates a new DateProvider.  Is private, so no other class can instantiate it.
     */
    private DateProvider() { 
    	sdf = new SimpleDateFormat("MMM dd, yyyy");
    	sdtf = new SimpleDateFormat("yyyy MMM dd HH:mm");
    }
 
    
    /**
     * Gets the single instance of DateProvider.  This is thread-safe.
     *
     * @return single instance of DateProvider
     */
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
    
    
    /**
     * Gets the date, right now
     *
     * @return the date, for right now
     */
    public Date now() {
        return Calendar.getInstance().getTime();
    }    

 
    /**
     * Checks if date1 is before date2 
     *
     * @param date1 the first Calendar date
     * @param date2 the second Calendar date 
     * @return true if date1 is before date2 
     */
    private static boolean isBefore(Date date1, Date date2) {    	  
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(date1);
        c2.setTime(date2);
        
        return (c1.before(c2)) ? true : false;
    }
      

    /**
     * Checks if a given date is greater than, (i.e. more previous than), x days ago
     *
     * @param dt a date to check against benchmark of xDays ago
     * @param xDays the offset of days to create a benchmark
     * @return true if given date is before benchmark of x days ago
     */
    public static boolean isGreaterThanXDaysAgo(Date dt, int xDays) {
        return isBefore(dt, xDaysAgo(xDays));
    } 
      
    
    /**
     * Gets a Date object, set to x days ago
     *
     * @param x the offset of days to used to create the Date object
     * @return the Date object, set to x days ago
     */     
    private static Date xDaysAgo(int x) {
    	//We need a neg number to go backwards; so if input is neg, then leave it alone
    	int xDaysBack = (x < 0) ? x : (x * -1);
    	
    	Calendar cc1 = Calendar.getInstance();
        cc1.add(Calendar.DAY_OF_MONTH, xDaysBack);
        return cc1.getTime();
    }
    
    
    /**
     * Makes a String of format: MMM DD, YYYY, from a Date object
     * 
     * @param dt the Date object to format
     * @return the String of format MMM DD, YYYY
     */
    public static String makeDateStr(Date dt) {
        // format: "Sep 6, 2009"
        return sdf.format(dt);
    } 
    
    
    /**
     * Makes a String of format: YYYY MMM DD HH:MM, from a Date object
     * 
     * @param dt the Date object to format
     * @return the String of format YYYY MMM DD HH:MM
     */
    public static String makeDateTimeStr(Date dt) {
        // format: "2009 Sep 6 10:34"
        return sdtf.format(dt);
    }   


}
