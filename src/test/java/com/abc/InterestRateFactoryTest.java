package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class InterestRateFactoryTest {

	public InterestRateFactoryTest() {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRateCalculator() {
		assertEquals("SavingsRate",
				(new InterestRateFactory().rateCalculator(AccountType.SAVINGS)
						.getClass().getSimpleName()));
		assertEquals("CheckingRate",
				(new InterestRateFactory().rateCalculator(AccountType.CHECKING)
						.getClass().getSimpleName()));
		assertEquals(
				"MaxiSavingsRate",
				(new InterestRateFactory().rateCalculator(
						AccountType.MAXI_SAVINGS).getClass().getSimpleName()));
	}

}
