package com.abc;

import java.util.Date;

public class Transaction {
    public final double amount;

    //TODO: hack for testing.
    protected Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }
    
    public Date getTransactionDate() {
        return transactionDate;
    }

    //TODO: allow for adjustments?
    public boolean isWithdrawal() {
        return amount < 0;
    }

    @Override
    public String toString() {
        return "Transaction [amount=" + amount + ", transactionDate=" + transactionDate + "]";
    }

    
}
