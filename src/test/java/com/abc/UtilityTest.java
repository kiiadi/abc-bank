package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class UtilityTest {

	public UtilityTest() {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testToDollars() {
		String expectedResult = "$1,000.00";
		String actualResult = Utility.toDollars(1000.0d);
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testFormat() {
		String expectedResult = "1 Account";
		String actualResult = Utility.format(1, "Account");
		assertEquals(expectedResult, actualResult);

		expectedResult = "5 Accounts";
		actualResult = Utility.format(5, "Account");
		assertEquals(expectedResult, actualResult);

		expectedResult = "No Accounts";
		actualResult = Utility.format(-5, "Account");
		assertEquals(expectedResult, actualResult);

		expectedResult = "No Accounts";
		actualResult = Utility.format(0, "Account");
		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void testNow() {
		assertNotNull(Utility.now());
	}

}
