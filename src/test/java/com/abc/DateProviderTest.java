/**
 * 
 */
package com.abc;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

/**
 * Class DateProviderTest
 *
 */
public class DateProviderTest {
	
	private Calendar cal = null;
	private int XDAYS;
	
	/**
	 * SetUp some Dates
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cal = Calendar.getInstance();
		XDAYS = 10;
	}

	
	/**
	 * Test the Value of "Now()"
	 */
	@Test
	public void testValueOfNow() {		
		Date now = DateProvider.getInstance().now();
		Date calNow = cal.getTime();
		assertEquals(now, calNow);
	}
	
	
	/**
	 * Test a date that is 10 Days Before XDAYS Ago
	 */
	@Test
	public void testDateIsTenDaysBeforeXDaysAgo() {
		cal.add(Calendar.DAY_OF_MONTH, -(XDAYS+10));
		Date _10daysBeforeXDAYS = cal.getTime();
		@SuppressWarnings("static-access")
		boolean check = DateProvider.getInstance().isGreaterThanXDaysAgo(_10daysBeforeXDAYS, XDAYS);
		assertEquals(true, check);
	}

	
	/**
	 * Test a date that is 1 Day Before XDAYS Ago
	 */
	@Test
	public void testDateIsOneDayBeforeXDaysAgo() {	
		cal.add(Calendar.DAY_OF_MONTH, -(XDAYS+1));
		Date _1dayBeforeXDAYS = cal.getTime();
		@SuppressWarnings("static-access")
		boolean check = DateProvider.getInstance().isGreaterThanXDaysAgo(_1dayBeforeXDAYS, XDAYS);
		assertEquals(true, check);
	}

	
	/**
	 * Test a date that is equal to XDAYS Ago
	 */
	@Test
	public void testDateIsEqualToXDaysAgo() {
		cal.add(Calendar.DAY_OF_MONTH, -XDAYS);
		Date equalToXDAYS = cal.getTime();
		@SuppressWarnings("static-access")
		boolean check = DateProvider.getInstance().isGreaterThanXDaysAgo(equalToXDAYS, XDAYS);
		assertEquals(false, check);
	}
	
	
	/**
	 * Test a date that is 1 Day After XDAYS Ago
	 */
	@Test
	public void testDateIsOneDayAfterXDaysAgo() {
		cal.add(Calendar.DAY_OF_MONTH, -(XDAYS-1));
		Date _1dayAfterXDAYS = cal.getTime();
		@SuppressWarnings("static-access")
		boolean check = DateProvider.getInstance().isGreaterThanXDaysAgo(_1dayAfterXDAYS, XDAYS);
		assertEquals(false, check);
	}
	
	
	/**
	 * Test a date that is 5 Days After XDAYS Ago
	 */	
	@Test
	public void testDateIsFiveDaysAfterXDaysAgo() {
		cal.add(Calendar.DAY_OF_MONTH, -(XDAYS-5));
		Date _5daysAfterXDAYS = cal.getTime();
		@SuppressWarnings("static-access")
		boolean check = DateProvider.getInstance().isGreaterThanXDaysAgo(_5daysAfterXDAYS, XDAYS);
		assertEquals(false, check);
	}
	
	
	/**
	 * Test making a String for the Date of Jul 04, 1776
	 */	
	@Test
	public void testMakeDateStr() {
		//Set to July 04, 1776 
		cal.set(1776, 6, 4);
		Date july04_1776 = cal.getTime();
		@SuppressWarnings("static-access")
		String july04Str = DateProvider.getInstance().makeDateStr(july04_1776);
		assertEquals("Jul 04, 1776", july04Str);
	}	
	
	
	/**
	 * Test making a String for the Date of 1776 Jul 04 10:52
	 */	
	@Test
	public void testMakeDateTimeStr() {
		//Set to July 04, 1776 10:52
		cal.set(1776, 6, 4, 10, 52);
		Date _1776_Jul_04_10_52 = cal.getTime();
		@SuppressWarnings("static-access")
		String july04TimeStr = DateProvider.getInstance().makeDateTimeStr(_1776_Jul_04_10_52);
		assertEquals("1776 Jul 04 10:52", july04TimeStr);
	}
	
}
