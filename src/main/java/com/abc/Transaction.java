package com.abc;

import java.util.Calendar;
import java.util.Date;

/**
 * Class representing transaction on a Customer account
 * @author Rakesh
 *
 */
public class Transaction {
	/**
	 * Amount of the transaction
	 */
	private final double amount;

	/**
	 * Value date of the transaction 
	 */
	private final Date transactionDate;

	/**
	 * Constructor
	 * @param amount
	 */
	public Transaction(double amount) {
		this.amount = amount;
		this.transactionDate = Calendar.getInstance().getTime();
	}

	/**
	 * Overloaded constructor
	 * @param amount
	 * @param transactionDate
	 */
	public Transaction(double amount, Date transactionDate) {
		this.amount = amount;
		this.transactionDate = transactionDate;
	}

	/**
	 * Transaction amount
	 * @return amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Transaction date
	 * @return date of transaction
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

}
