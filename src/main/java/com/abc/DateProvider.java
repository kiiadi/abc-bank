package com.abc;

import java.util.Calendar;
import java.util.Date;

/**
 * Date provider
 * @author Jeff
 *
 */
public class DateProvider {
    private static DateProvider instance = null;

    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }


}
