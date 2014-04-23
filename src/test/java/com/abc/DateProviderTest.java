package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DateProviderTest {


	@Test
	public void testDateProviderSingelton() {

		assertEquals(DateProvider.getInstance(), DateProvider.getInstance());
	}

}
