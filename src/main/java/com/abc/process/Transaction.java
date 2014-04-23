package com.abc.process;

import com.common.utils.DateProvider;

import java.util.Date;

public class Transaction {

    public final double amount;
    private Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }

    public Transaction(double amount, Date transactionDate){
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public double getAmount() {
        return amount;
    }
}
