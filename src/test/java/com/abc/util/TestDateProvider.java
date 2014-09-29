package com.abc.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		assertEquals(dateProvider, DateProvider.getInstance());
	}
	
	@Test
	public void shouldGetDaysBetween() throws ParseException{
		final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		assertEquals(10, dateProvider.daysBetween(format.parse("01/01/2014"), format.parse("01/11/2014")));
	}
}
