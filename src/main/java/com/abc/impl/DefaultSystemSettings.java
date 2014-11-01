package com.abc.impl;

import com.abc.impl.util.DateTimeUtil;
import com.abc.model.api.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class DefaultSystemSettings implements SystemSettings {

    @Override
    public Date getSystemDate() {
        //Joda Time LocalDate would be more appropriate here
        return DateTimeUtil.removeTimeInformation(new Date());
    }
}
