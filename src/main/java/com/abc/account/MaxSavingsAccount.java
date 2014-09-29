package com.abc.account;

import java.util.ArrayList;

import com.abc.account.interest.calculator.MaxSavingsAccountInterestCalculator;
import com.abc.transaction.ITransaction;
import com.abc.util.DateProvider;


/**
 * @Todo wire the interest calculator.
 * 
 * @author Sanju Thomas
 *
 */
public class MaxSavingsAccount extends Account implements IAccount{

	public MaxSavingsAccount(final String number){
		super(number);
		super.interestCalculator = new MaxSavingsAccountInterestCalculator(0.1, 5);
		super.transactions = new ArrayList<ITransaction>();
		super.transactionValidators = getTransactionValidators();
		openingDate = DateProvider.getInstance().now();
	}
	
	public int hashCode() {
		return super.hashCode();
	}

	public boolean equals(Object obj){
		return super.equals(obj);
	}
}
