package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InterestRateTest {
	@Test
	public void testCalculateEarnedSavings() {
		Double amount = 100.0d;
		Double expectedResult = 0.1d;

		Double actualResult = new SavingsRate().calculateEarned(amount);
		assertEquals(expectedResult, actualResult);

		amount = 1000.0d;
		expectedResult = 1.0d;
		actualResult = new SavingsRate().calculateEarned(amount);
		assertEquals(expectedResult, actualResult);

		amount = 2000.0d;
		expectedResult = 3.0d;
		actualResult = new SavingsRate().calculateEarned(amount);
		assertEquals(expectedResult, actualResult);

		amount = -2000.0d;
		expectedResult = -2.0d;
		actualResult = new SavingsRate().calculateEarned(amount);
		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void testCalculateEarnedChecking() {
		Double amount = 100.0d;
		Double expectedResult = 0.1d;

		Double actualResult = new CheckingRate().calculateEarned(amount);
		assertEquals(expectedResult, actualResult);

		amount = 1000.0d;
		expectedResult = 1.0d;
		actualResult = new CheckingRate().calculateEarned(amount);
		assertEquals(expectedResult, actualResult);

		amount = 2000.0d;
		expectedResult = 2.0d;
		actualResult = new CheckingRate().calculateEarned(amount);
		assertEquals(expectedResult, actualResult);

		amount = -2000.0d;
		expectedResult = -2.0d;
		actualResult = new CheckingRate().calculateEarned(amount);
		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void testCalculateEarnedMaxiSavings() {
		Double amount = 100.0d;
		Double expectedResult = 2.0d;

		Double actualResult = new MaxiSavingsRate().calculateEarned(amount);
		assertEquals(expectedResult, actualResult);

		amount = 1000.0d;
		expectedResult = 20.0d;
		actualResult = new MaxiSavingsRate().calculateEarned(amount);
		assertEquals(expectedResult, actualResult);

		amount = 2000.0d;
		expectedResult = 70.0d;
		actualResult = new MaxiSavingsRate().calculateEarned(amount);
		assertEquals(expectedResult, actualResult);

		amount = 3000.0d;
		expectedResult = 170.0d;
		actualResult = new MaxiSavingsRate().calculateEarned(amount);
		assertEquals(expectedResult, actualResult);

		amount = -2000.0d;
		expectedResult = -40.0d;
		actualResult = new MaxiSavingsRate().calculateEarned(amount);
		assertEquals(expectedResult, actualResult);

		amount = -3000.0d;
		expectedResult = -60.0d;
		actualResult = new MaxiSavingsRate().calculateEarned(amount);
		assertEquals(expectedResult, actualResult);

	}

}
