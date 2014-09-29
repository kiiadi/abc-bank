package com.abc.account;

import java.util.ArrayList;

import com.abc.transaction.ITransaction;
import com.abc.util.DateProvider;


/**
 * 
 * @author Sanju Thomas
 *
 */
public class MaxSavingsAccount extends Account implements IAccount{

	public MaxSavingsAccount(){
		super.transactions = new ArrayList<ITransaction>();
		super.transactionValidators = getTransactionValidators();
		openingDate = DateProvider.getInstance().now();
	}
}
