package com.abc.account;

import java.util.ArrayList;

import com.abc.account.interest.calculator.SavingsAccountInterestCalculator;
import com.abc.transaction.ITransaction;
import com.abc.util.DateProvider;


/**
 * @Todo wire the interest calculator.
 * 
 * @author Sanju Thomas
 *
 */
public class CheckingAccount extends Account implements IAccount{

	public CheckingAccount(final String number){
		super(number);
		super.interestCalculator = new SavingsAccountInterestCalculator(0.1, 0.2);
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
