package com.abc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class TimeTuner extends DateProvider {
	private Date _now = null;
	
	@Override
	public Date now() {
		return _now;
	}
	public void now(Date now) {
		if (_now != null && _now.after(now))
			throw new IllegalArgumentException("time moves only forward");
		this._now = now;
	}
	public void now(String formatted) {
		now(parseISO8601Date(formatted).getTime());
	}
	
	static TimeTuner mock() {
		TimeTuner instance = new TimeTuner();
		DateProvider.instance = instance;
		return instance;
	}
	
	private static final ThreadLocal<SimpleDateFormat> ISO8601 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	public static Calendar parseISO8601Date(String formatted) {
		Calendar date = Calendar.getInstance();
		try {
			date.setTime(ISO8601.get().parse(formatted));
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
		return date;
	}
}
