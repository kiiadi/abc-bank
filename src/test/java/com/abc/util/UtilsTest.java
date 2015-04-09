package com.abc.util;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void testDecimalRoundedDown() {
		BigDecimal amount = new BigDecimal(100000.123D);
		assertEquals(Utils.displayRoundedWithCurrency(amount), "$100,000.12");
	}
	
	@Test
	public void testDecimalRoundedUp() {
		BigDecimal amount = new BigDecimal(100000.125D);
		assertEquals(Utils.displayRoundedWithCurrency(amount), "$100,000.13");
	}
	
	@Test
	public void testNormalizeDay() throws ParseException {
		Date rawDate = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2015/01/01 10:12:15");
		
		assertEquals(Utils.normalizeDay(rawDate), new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2015/01/01 00:00:00"));
	}
	
	@Test
	public void testGetDaysBetween() throws ParseException {
		Date startDate = new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01");
		Date endDate = new SimpleDateFormat("yyyy/MM/dd").parse("2015/02/01");
		
		assertEquals(Utils.getDaysBetween(startDate, endDate), 31);
	}
}
