package com.abc;

import java.util.Date;

public class Transaction {
    private final double amount;

    private Date transactionDate;

    private TransactionType type;


    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", transactionDate=" + transactionDate +
                ", type=" + type +
                '}';
    }

    public Transaction(TransactionType type, double amount) {
        this.transactionDate = DateProvider.INSTANCE.now();
        this.type = type;
        this.amount = amount;

    }

}
