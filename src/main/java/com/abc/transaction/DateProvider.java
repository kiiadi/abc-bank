package com.abc.transaction;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {

    private DateProvider() {
    }

    public static Date now() {
        return Calendar.getInstance().getTime();
    }
}
