package com.abc.transaction;

import java.util.Calendar;
import java.util.Date;

public class DefaultDateProvider implements DateProvider {

    public Date now() {
        return Calendar.getInstance().getTime();
    }

    public Date now(int plusDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.DAY_OF_YEAR, plusDays);
        return cal.getTime();
    }

}
