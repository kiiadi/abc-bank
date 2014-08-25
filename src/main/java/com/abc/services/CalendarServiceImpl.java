package com.abc.services;

import java.util.Date;

public class CalendarServiceImpl implements CalendarService {
    @Override
    public Date getNow() {
        return new Date();
    }
}
