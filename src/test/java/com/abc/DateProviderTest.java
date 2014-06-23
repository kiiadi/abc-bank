package com.abc;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class DateProviderTest {

	@Test
    public void testDateDiff() {
		Calendar date1 = Calendar.getInstance();
		date1.set(2014, 6, 15);
		Calendar date2 = Calendar.getInstance();
		date2.set(2014, 6, 20);
		
		long diff = DateProvider.getInstance().dateDiff(date1.getTime(), date2.getTime());
		assertEquals(5, diff);
	}
}
