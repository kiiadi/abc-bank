package com.abc;

import java.util.Date;

public class Transaction {
    public final double amount;

    private Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }
    
    public Date getTransactionDate() {
        return transactionDate;
    }

    //could be better - to allow for adjustments.
    public boolean isWithdrawl() {
        return amount < 0;
    }

    @Override
    public String toString() {
        return "Transaction [amount=" + amount + ", transactionDate=" + transactionDate + "]";
    }

    
}
