package com.abc.util;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
	
	public Date now() {
		return Calendar.getInstance().getTime();
	}
}
