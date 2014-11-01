package com.abc.impl;

import com.abc.impl.helper.DateTimeHelper;
import com.abc.model.api.*;

import java.util.Date;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class DefaultSystemSettings implements SystemSettings {

    @Override
    public Date getSystemDate() {
        //Joda Time LocalDate would be more appropriate here
        return DateTimeHelper.removeTimeInformation(new Date());
    }
}
