package com.abc.account;

import java.util.ArrayList;

import com.abc.transaction.ITransaction;
import com.abc.util.DateProvider;


/**
 * 
 * @author Sanju Thomas
 *
 */
public class CheckingAccount extends Account implements IAccount{

	public CheckingAccount(){
		super.transactions = new ArrayList<ITransaction>();
		super.transactionValidators = getTransactionValidators();
		openingDate = DateProvider.getInstance().now();
	}

}
