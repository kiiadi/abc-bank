package com.abc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class AccountSuperSavings extends Account {
	private static final double RATE = 0.05;
	private static final int noWithdrawalPeriod = 10; //No withdrawals means extra savings

	public double interestEarnedIn(long days, double onAmount) {
		return onAmount * days / DAYS_IN_YEAR * DEFAULT_RATE;
	}

	public double interestEarnedIn(long days, double onAmount, boolean didWithdrawInBlackoutPeriod) {
		return onAmount * days / DAYS_IN_YEAR * (didWithdrawInBlackoutPeriod ? DEFAULT_RATE : RATE);
	}

	public String statementHeading() {
		return "Super Savings Account";
	}
	
	public double interestEarnedInPeriod(double onAmount, Instant start, Instant end, Instant latestWidtdrawalOn) {
		double interest = 0.0;
		Instant widthdrawalPlus10 = latestWidtdrawalOn.plus(noWithdrawalPeriod, ChronoUnit.DAYS);
		long daysWorse = 0; //inside the withdrawal window
		long daysBetter = 0; //outside of the withdrawal window
		if(widthdrawalPlus10.isBefore(start)) {
			daysBetter = ChronoUnit.DAYS.between(start, end);
		} else if (widthdrawalPlus10.isAfter(end)) {
			daysWorse = ChronoUnit.DAYS.between(start, end);
		} else {
			daysWorse = ChronoUnit.DAYS.between(start, widthdrawalPlus10);
			daysBetter = ChronoUnit.DAYS.between(widthdrawalPlus10, end);
		}
		if(daysWorse > 0) {
			interest += interestEarnedIn(daysWorse, onAmount, true);
		}
		if(daysBetter > 0) {
			interest += interestEarnedIn(daysBetter, onAmount, false);
		}
		return interest;
	}
}
