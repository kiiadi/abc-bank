package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
    //private static volatile DateProvider instance;
    private static final Calendar  calendar = Calendar.getInstance();

    private DateProvider(){}

    /*
     TODO Don't really know why a singleton was attempted here since the only useful method
      is now() which anyways uses a Calendar Instance. below is a DCL impl, commented out however.


    public static DateProvider getInstance() {

        if (instance == null) {
            synchronized (DateProvider.class) {
                if(instance == null) {
                    instance = new DateProvider();
                }
            }
        }
        return instance;
    }
    */


    public static Date now() {
        return calendar.getTime();
    }
}
