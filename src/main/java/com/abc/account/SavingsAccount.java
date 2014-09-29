package com.abc.account;

import java.util.ArrayList;

import com.abc.account.interest.calculator.IInterestCalculator;
import com.abc.transaction.ITransaction;
import com.abc.util.DateProvider;


/**
 * 
 * @author Sanju Thomas
 *
 */
public class SavingsAccount extends Account implements IAccount{
	
	public SavingsAccount(){
		super.transactions = new ArrayList<ITransaction>();
		super.transactionValidators = getTransactionValidators();
		openingDate = DateProvider.getInstance().now();
	}
}
