package com.abc.calculator;

import java.util.Date;

import com.abc.util.Utils;

public class InterestCalculator {
	
	public static double calculate(double principal, double rate, Date startingDate, Date endingDate) {
		long days = Utils.getDaysBetween(startingDate, endingDate);
		return principal * (rate/100/365) * days;
	}
	
}
