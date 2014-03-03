package com.abc;

/**
 * 
 */

import java.util.Date;

public class DateProvider {
    
    private static DateProvider instance = null;
    
    public static DateProvider getInstance() {
        
        if (instance == null) {
            instance = new DateProvider();
        }
        
        return instance;
    }
    
    public long now() {
        return System.currentTimeMillis();
    }
    
    /*
    for testing use only.
    */
    public synchronized void setInstance(DateProvider provider) {
        instance = provider;
    }
    
}
