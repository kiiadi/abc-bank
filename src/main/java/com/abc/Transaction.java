package com.abc;

import java.time.Instant;

/**
 * 
 * @author Alex Gordon 
 * Transaction class holds an amount and a moment of time
 *         when the transaction created. 
 *         for deposit - amount must NOT be
 *         negative 
 *         for withdraw - amount must NOT be positive
 * 
 */
public class Transaction {

	// enum: transaction types
	public static enum TRANSACTION_TYPE {
		DEPOSIT("deposit"), 
		WITHDRAW("withdraw");

		private final String transactionTypeDescription;

		TRANSACTION_TYPE(String transactionTypeDescription) {
			this.transactionTypeDescription = transactionTypeDescription;
		}

		public String getTransactionTypeDescription() {
			return transactionTypeDescription;
		}

	}

	public final double amount;
	private Instant transactionInstant;
	private TRANSACTION_TYPE transactionType;
	private String transactionTypeDescription;

	public Transaction(Instant instant, double amount) {
		this.amount = amount;
		this.transactionInstant = instant;
		if (amount < 0.0) {
			this.transactionType = TRANSACTION_TYPE.WITHDRAW;
			this.transactionTypeDescription = TRANSACTION_TYPE.WITHDRAW.getTransactionTypeDescription();
		} else if (amount > 0.0) {
			this.transactionType = TRANSACTION_TYPE.DEPOSIT; 
			this.transactionTypeDescription = TRANSACTION_TYPE.DEPOSIT.getTransactionTypeDescription();
		} else {
			throw new IllegalArgumentException(Constants.ZERO_AMOUNT_ERROR);
		}
	}

	public double getAmount() {
		return amount;
	}

	public Instant getTransactionInstant() {
		return transactionInstant;
	}

	public TRANSACTION_TYPE getTransactionType() {
		return transactionType;
	}

	public String getTransactionTypeDescription() {
		return transactionTypeDescription;
	}

	public String toString() {
		return "Transaction: time=" + this.transactionInstant +  ", type=" + this.transactionTypeDescription  + ", amount=" + Constants.toDollars(this.amount);
	}

}
