package com.abc.transaction;

import java.math.BigDecimal;

public class Deposit extends Transaction {

	public Deposit(BigDecimal amount) {
		super(amount);
	}

	@Override
	public BigDecimal processTransaction(BigDecimal currentBalance) {
		return currentBalance.add(getTransAmount());
	}

	@Override
	public String getTransactionType() {
		return TransactionType.DEPOSIT.getDisplayName();
	}
}
