package com.abc;

import java.util.Calendar;
import java.util.Date;

/**
 * POJO used for account transactions
 */
public class Transaction {
    private final double amount;
    private final Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = Calendar.getInstance().getTime();
    }

    /**
     * Only used to create Mock objects for testing.
     * @param amount
     * @param date
     */
    @Deprecated
    public Transaction(double amount, Calendar date){
        this.amount = amount;
        this.transactionDate = date.getTime();
    }

    /**
     * Gets Transaction Amount
     * @return
     */
    public double getAmount(){
        return amount;
    }

    /**
     * Gets Transaction Date
     * @return
     */
    public Date getTransactionDate(){
        return transactionDate;
    }
}
