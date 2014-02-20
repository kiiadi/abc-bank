package com.abc;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User: mchernyak
 * Date: 2/19/14
 * Time: 5:31 PM
 */
public class DateProviderTest {

	@Test
	public void testDateProviderInstance() {
		// this will not compile as class is a Singleton
		// DateProvider dp = new DateProvider();
		DateProvider dp = DateProvider.getInstance();
		assertNotNull(dp);
	}

	@Test
	public void testGetDateByYMD() {
		Date d1 = DateProvider.getInstance().getDateByYMD(1995, 6, 30);
		Date d2 = DateProvider.getInstance().getDateByYMD(2014, 2, 20);
		assertEquals("Fri Jun 30 00:00:00 EDT 1995", d1.toString());
		assertEquals("Thu Feb 20 00:00:00 EST 2014", d2.toString());
	}

	@Test
	public void testDateFromString() {
		Date d1 = DateProvider.getInstance().dateFromString("20140131");
		Date d2 = DateProvider.getInstance().getDateByYMD(2014, 1, 31);
		assertEquals(d1, d2);
	}

	@Test
	public void testAddDays() {
		Date d = DateProvider.getInstance().getDateByYMD(2014, 2, 15);
		Date d0 = DateProvider.getInstance().addDays(d, 0);
		Date d1 = DateProvider.getInstance().addDays(d, -14);
		Date d2 = DateProvider.getInstance().addDays(d, 15);
		Date dt1 = DateProvider.getInstance().getDateByYMD(2014, 2, 1);
		Date dt2 = DateProvider.getInstance().getDateByYMD(2014, 3, 2);

		assertEquals(d, d0);
		assertEquals(d, d0);
		assertEquals(d1, dt1);
		assertEquals(d2, dt2);
	}

	@Test
	public void testCalendarDaysDifference() {
		Date d1 = DateProvider.getInstance().getDateByYMD(2014, 2, 1);
		Date d2 = DateProvider.getInstance().getDateByYMD(2014, 2, 11);
		Date d3 = DateProvider.getInstance().getDateByYMD(2014, 3, 1);
		assertEquals(10, DateProvider.getInstance().calendarDaysDifference(d1, d2));
		assertEquals(28, DateProvider.getInstance().calendarDaysDifference(d1, d3));
	}

}
