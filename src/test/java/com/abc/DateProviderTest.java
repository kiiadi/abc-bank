package com.abc;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Calendar;

public class DateProviderTest {

	/**
	 * Simple test to check DateProvider returns current time
	 */
	@Test
	public void testDateProvider() {
		assertEquals(Calendar.getInstance().getTime().toString(), DateProvider
				.getInstance().now().toString());
	}

}
