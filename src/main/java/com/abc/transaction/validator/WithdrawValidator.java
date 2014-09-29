package com.abc.transaction.validator;

import com.abc.account.IAccount;
import com.abc.exception.NotEnoughBalanceException;
import com.abc.transaction.ITransaction;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class WithdrawValidator implements ITransactionValidator {
	
	public void validate(IAccount account, ITransaction transaction) throws NotEnoughBalanceException {
		if(transaction.getAmount() > account.getBalance()){
			throw new NotEnoughBalanceException(account.getBalance(), transaction.getAmount());
		}
	}

}
