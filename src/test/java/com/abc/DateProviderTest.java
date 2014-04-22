package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DateProviderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDateProviderSingelton() {

		assertEquals(DateProvider.getInstance(), DateProvider.getInstance());
	}

}
