package com.abc;

import java.util.Calendar;
import java.util.Date;

/**
 * This class is a helper class for Date related operations
 * 
 * @author Manish
 *
 */
public class DateProvider {
    private static DateProvider instance = null;

    /**
     * Returns a singleton instance of this class.
     * @return
     */
    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    /**
     * Returns the current date instance
     * @return
     */
    public Date now() {
        return Calendar.getInstance().getTime();
    }
    
}
