package com.abc.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InterestRateCalculator {

	/**
     * Verified with online calculators.
     * $3000 compounded daily with term of 1 yr yields 153.80 as the interest.
     * http://www.thecalculatorsite.com/finance/calculators/compoundinterestcalculator.php
     * This use case is part of the BankTest.
     */
	public static BigDecimal dailyCompoundedInterest(BigDecimal P, double r) {

		/**
		 * A = P (1 + r/n) ^ nt
		 * 
		 * For daily compound interest, n = 365. 
		 * 
		 * Assuming term of 1 Yr, t = 1
		 */
		double A = P.doubleValue()
				* Math.pow((1.00d + (r / 365.00d)), 365.00d * 1d);
		return new BigDecimal(A).subtract(P).setScale(2, RoundingMode.HALF_UP);
	}

	public static BigDecimal monthlyCompoundedInterest(BigDecimal P, double r) {

		/**
		 * A = P (1 + r/n) ^ nt
		 * 
		 * For monthly compound interest, n = 12.
		 * 
		 * Assuming term of 1 Yr, t = 1
		 */
		double A = P.doubleValue()
				* Math.pow((1.00d + (r / 12.00d)), 12.00d * 1d);
		return new BigDecimal(A).subtract(P).setScale(2, RoundingMode.HALF_UP);
	}

	public static BigDecimal yearlyCompoundedInterest(BigDecimal P, double r) {

		/**
		 * A = P (1 + r/n) ^ nt
		 * 
		 * For yearly compound interest, n = 1. 
		 * 
		 * Assuming term of 1 Yr, t = 1
		 */
		double A = P.doubleValue()
				* Math.pow((1.00d + (r / 1.00d)), 1.00d * 1d);
		return new BigDecimal(A).subtract(P).setScale(2, RoundingMode.HALF_UP);
	}

}
