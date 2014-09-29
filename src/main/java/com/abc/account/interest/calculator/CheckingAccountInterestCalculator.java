package com.abc.account.interest.calculator;

import com.abc.account.IAccount;
import com.abc.util.DateProvider;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class CheckingAccountInterestCalculator extends InterestCalculator implements IInterestCalculator{
	
	private double rate;
	
	public CheckingAccountInterestCalculator(final double rate){
		this.rate = rate;
	}

	public double calculate(IAccount account) {
		return super.calculate(account.getBalance(), rate, account.getOpeningDate(), DateProvider.getInstance().now());
	}
	
}
	