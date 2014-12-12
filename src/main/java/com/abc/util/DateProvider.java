package com.abc.util;

import java.util.Calendar;

public class DateProvider {
	
	public String now() {
		return Calendar.getInstance().getTime().toString();
	}
}
