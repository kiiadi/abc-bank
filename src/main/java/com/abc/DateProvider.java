package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider implements DateProviderInterface {
    private static DateProviderInterface instance = null;

    public static DateProviderInterface getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }
    
    public static void setInstance(DateProviderInterface obj){
    	instance = obj;
    }
    
    public Date now() {
        return Calendar.getInstance().getTime();
    }
}
