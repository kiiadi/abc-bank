package com.abc.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestDateProvider {
	
	private DateProvider dateProvider;
	
	@Before
	public void setUp(){
		dateProvider = DateProvider.getInstance();
	}

	@Test
	public void shouldGetSameInstance(){
		assertNotNull(DateProvider.getInstance());
		assertEquals(dateProvider, DateProvider.getInstance());
	}
	
	@Test
	public void shouldGetNow(){
		final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		final Date now = DateProvider.getInstance().now();
		assertEquals(format.format(Calendar.getInstance().getTime()), format.format(now));
	}
	
	@Test
	public void shouldGetDaysBetween() throws ParseException{
		final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		assertEquals(10, dateProvider.daysBetween(format.parse("01/01/2014"), format.parse("01/11/2014")));
	}
}
