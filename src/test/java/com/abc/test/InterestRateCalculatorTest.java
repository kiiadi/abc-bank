package com.abc.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.abc.util.InterestRateCalculator;

public class InterestRateCalculatorTest {

	@Test
	 /**
     * Verified with online calculators.
     * $3000 compounded daily with term of 1 yr yields 153.80 as the interest.
     * http://www.thecalculatorsite.com/finance/calculators/compoundinterestcalculator.php
     */
	public void testDailyCompundingInterest() {
		BigDecimal P = new BigDecimal("3000.00");
		double r = 0.05d;
		BigDecimal interest = InterestRateCalculator.dailyCompoundedInterest(P, r);
		assertEquals(153.80, interest.doubleValue(), 1e-15);
	}
}
