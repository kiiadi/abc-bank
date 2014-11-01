package com.abc.impl.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class DateTimeUtil {

    public static Date pushDateForwardByNumberOfDays(Date date, int numberOfDays) {

        Calendar asCalendar = Calendar.getInstance();
        asCalendar.setTime(date);
        asCalendar.add(Calendar.DAY_OF_MONTH,numberOfDays);

        return asCalendar.getTime();
    }

    public static Date removeTimeInformation(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return formatter.parse(formatter.format(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
