package com.abc;

import java.util.Date;

/**
 * This class represents the transaction performed on an account
 * for a customer.
 * 
 * @author Manish
 *
 */
public class Transaction {
    public final double amount;
    private final Date transactionDate;

    /**
     * Constructs the Transaction object with amount as parameter
     * @param amount
     */
    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }

    /**
     * Returns amount for this transaction
     * @return
     */
	public double getAmount() {
		return amount;
	}

    /**
     * Returns date on which this transaction was executed
     * @return
     */
	public Date getTransactionDate() {
		return transactionDate;
	}
}
