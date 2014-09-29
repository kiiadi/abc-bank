package com.abc.account.interest.calculator;

import com.abc.account.IAccount;
import com.abc.transaction.ITransaction;
import com.abc.transaction.Withdraw;
import com.abc.util.DateProvider;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class MaxSavingsAccountInterestCalculator extends InterestCalculator implements IInterestCalculator{

	private double defaultRate;
	
	private double maxSavingRate;
	
	public MaxSavingsAccountInterestCalculator(final double defaultRate, final double maxSavingRate){
		this.defaultRate = defaultRate;
		this.maxSavingRate = maxSavingRate;
	}
	
	public double calculate(final IAccount account) {
		double interest;
		if(isEligibleForMaxSavingsRate(account)){
			interest = super.calculate(account.getBalance(), maxSavingRate, account.getOpeningDate(), 
					DateProvider.getInstance().now());
		}else{
			interest = super.calculate(account.getBalance(), defaultRate, account.getOpeningDate(), 
					DateProvider.getInstance().now());
		}
		return interest;
	}
	
	/**
	 * In real implementation it will be one query with transaction date >= current date - 10 and transaction type withdrawal
	 * 
	 * @param account
	 * @return
	 */
	private boolean isEligibleForMaxSavingsRate(final IAccount account){
		boolean eligibleForMaxSavigsRate = true;
		for(final ITransaction transaction : account.getTransactions()){
			if(transaction instanceof Withdraw){
				final int days = DateProvider.getInstance().daysBetween(transaction.getDate(), DateProvider.getInstance().now());
				if(days <= 10){
					eligibleForMaxSavigsRate = false;
					break;
				}
			}
		}
		return eligibleForMaxSavigsRate;
	}

}
