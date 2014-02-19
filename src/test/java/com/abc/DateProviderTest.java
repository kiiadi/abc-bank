package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * User: mchernyak
 * Date: 2/19/14
 * Time: 5:31 PM
 */
public class DateProviderTest {

	@Test
	public void testDateProvider() {
		// this should not compile as class is a Singleton
		// DateProvider dp = new DateProvider();

		DateProvider dp = DateProvider.getInstance();
		assertNotNull(dp);
	}

}
