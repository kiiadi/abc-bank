package com.abc;

import java.time.LocalDate;

class DateProvider {
    private static DateProvider instance = null;
    public static void setInstance(DateProvider provider) {
    	instance = provider;
    }
    
    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    public LocalDate now() {
        return LocalDate.now();
    }
}
