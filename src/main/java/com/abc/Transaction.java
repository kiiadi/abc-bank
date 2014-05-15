package com.abc;

import java.util.Date;

/**
 * The Transaction class
 * @author Jeff
 *
 */
public class Transaction {
    public final double amount;

    private Date transactionDate;

    /**
     * Create a new Transaction object
     * @param amount  - the amount of this transaction
     */
    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = Utils.now();
    }

}
