package com.abc;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;


/**
 * User: mchernyak
 * Date: 2/19/14
 * Time: 5:31 PM
 */
public class DateUtilTest {

	@Test
	public void testGetDateByYMD() {
		Date d1 = DateUtil.dateFromYMD(1995, 6, 30);
		Date d2 = DateUtil.dateFromYMD(2014, 2, 20);
		assertEquals("Fri Jun 30 00:00:00 EDT 1995", d1.toString());
		assertEquals("Thu Feb 20 00:00:00 EST 2014", d2.toString());
	}

	@Test
	public void testDateFromString() {
		Date d1 = DateUtil.dateFromYYYYMMDDString("20140131");
		Date d2 = DateUtil.dateFromYMD(2014, 1, 31);
		assertEquals(d1, d2);
	}

	@Test
	public void testAddDays() {
		Date d = DateUtil.dateFromYMD(2014, 2, 15);
		Date d0 = DateUtil.addDays(d, 0);
		Date d1 = DateUtil.addDays(d, -14);
		Date d2 = DateUtil.addDays(d, 15);
		Date dt1 = DateUtil.dateFromYMD(2014, 2, 1);
		Date dt2 = DateUtil.dateFromYMD(2014, 3, 2);

		assertEquals(d, d0);
		assertEquals(d, d0);
		assertEquals(d1, dt1);
		assertEquals(d2, dt2);
	}


	@Test
	public void testCalendarDaysDifference() {
		Date d1 = DateUtil.dateFromYMD(2014, 2, 1);
		Date d2 = DateUtil.dateFromYMD(2014, 2, 11);
		Date d3 = DateUtil.dateFromYMD(2014, 3, 1);
		assertEquals(10, DateUtil.calendarDaysDifference(d1, d2));
		assertEquals(28, DateUtil.calendarDaysDifference(d1, d3));
	}
}
