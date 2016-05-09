package com.abc;

import java.util.Calendar;
import java.util.Date;

/**
 * The transaction object representing every transaction in the account
 */

public class Transaction {
    public final double amount;

    public Date transactionDate;

    /**
     * Constructor to instantiate the transaction object
     * @param amount the amount to be deposited or withdrawn
     */
    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = Calendar.getInstance().getTime();
    }

    /**
     * Getter for the amount that is deposited or withdrawn
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Getter for the transaction date on which the transaction happened
     * @return the transaction date
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

}
