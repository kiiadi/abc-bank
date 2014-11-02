package com.abc.dummy;

import com.abc.impl.helper.DateTimeHelper;
import com.abc.model.api.DateProvider;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by alexandrkoller on 31/10/2014.
 */
public class DummyDateProvider implements DateProvider {

    @Override
    public Date getSystemDate() {
        //this is way in the future for the tests
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,10);
        return DateTimeHelper.removeTimeInformation(calendar.getTime());
    }
}
