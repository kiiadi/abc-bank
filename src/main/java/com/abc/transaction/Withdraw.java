package com.abc.transaction;

import java.math.BigDecimal;

import com.abc.exception.InsufficientFundsException;
import com.abc.exception.InvalidTransactionException;
import com.abc.util.Constants;

public class Withdraw extends Transaction {

	public Withdraw(BigDecimal amount) {
		super(amount);
	}

	@Override
	public BigDecimal processTransaction(BigDecimal currentBalance) {
		return currentBalance.subtract(getTransAmount());
	}

	@Override
	public String getTransactionType() {
		return TransactionType.WITHDRAW.getDisplayName();
	}

	@Override
	public void validate(BigDecimal currentBalance) throws InvalidTransactionException {
		super.validate(currentBalance);
		
		if (getTransAmount().compareTo(currentBalance) > 0) {
    		throw new InsufficientFundsException(Constants.WITHDRAWAL_EXCEEDS_ERROR);
		}
	}
}
