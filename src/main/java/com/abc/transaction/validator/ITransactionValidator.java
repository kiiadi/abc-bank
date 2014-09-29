package com.abc.transaction.validator;

import com.abc.account.IAccount;
import com.abc.exception.ValidationException;
import com.abc.transaction.ITransaction;

/**
 * 
 * @author Sanju Thomas
 *
 */
public interface ITransactionValidator {
	
	public void validate(final IAccount account, final ITransaction transaction) throws ValidationException;
}
