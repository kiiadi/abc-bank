package com.abc.account.interest.calculator;

import java.text.DecimalFormat;
import java.util.Date;

import com.abc.util.DateProvider;

/**
 * 
 * @author Sanju Thomas
 *
 */
public abstract class InterestCalculator implements IInterestCalculator{
	
	protected double calculate(final double principal, final double rate, final Date startingDate, final Date endingDate) {
		final int days = DateProvider.getInstance().daysBetween(startingDate, endingDate);
		final double iterest = ((((principal * rate) / 100) / 365) * days);
		final DecimalFormat doubleFormat = new DecimalFormat("#.##");      
		return Double.valueOf(doubleFormat.format(iterest));
	}

	
}
