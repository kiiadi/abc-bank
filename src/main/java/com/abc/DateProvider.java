package com.abc;

import java.util.Calendar;

/**
 * @deprecated  Since Calendar is a singleton we don't need a separate class to get current time<br>
 *              Use {@link Calendar} directly instead  
 */
import java.util.Date;

@Deprecated
public class DateProvider {
    private static DateProvider instance = null;

    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
}
