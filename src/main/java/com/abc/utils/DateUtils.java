/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abc.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author hiecaxb
 */
public abstract class DateUtils {

    public static long getDayDifference(long fromDate, long toDate) {
        
        long diffInDays;
        long diffInMillis = toDate - fromDate;
        
        if (Long.signum(diffInMillis) < 0) {
            diffInMillis = diffInMillis * -1L;
        }
        
        diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        
        return diffInDays;
    }
    
    public static String formatToDateTime(long millis) {
        
        String formatedDateTime = null;
        Calendar calendar = null;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss.SSS");
        
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        
        formatedDateTime = dateFormat.format(calendar.getTime());
        
        return formatedDateTime;
    }
    
    public static String formatToDate(long millis) {
        
        String formatedDateTime = null;
        Calendar calendar = null;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
        
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        
        formatedDateTime = dateFormat.format(calendar.getTime());
        
        return formatedDateTime;
    }
    
}
