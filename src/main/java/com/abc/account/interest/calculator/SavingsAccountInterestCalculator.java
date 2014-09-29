package com.abc.account.interest.calculator;

import com.abc.account.IAccount;
import com.abc.util.DateProvider;

/**
 * @Todo 
 * 
 * Rates should be wired via a rule configuration in real implementation.
 * 
 * @author Sanju Thomas
 *
 */
public class SavingsAccountInterestCalculator extends InterestCalculator implements IInterestCalculator{

	private double rateForFirst1000;
	private double rateForRemaining;
	
	public SavingsAccountInterestCalculator(final double rateForFirst1000, final double rateForRemaining){
		this.rateForFirst1000 = rateForFirst1000;
		this.rateForRemaining = rateForRemaining;
	}
	
	public double calculate(IAccount account) {
		double interest;
		if(account.getBalance() > 1000.00){
			interest = super.calculate((account.getBalance() - 1000), 
					rateForRemaining, account.getOpeningDate(), DateProvider.getInstance().now());
			interest += super.calculate(1000, rateForFirst1000, account.getOpeningDate(), DateProvider.getInstance().now());
		}else{
			interest = super.calculate(account.getBalance(), rateForFirst1000, account.getOpeningDate(), 
					DateProvider.getInstance().now());
		}
		return interest;
	}
}
