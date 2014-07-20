package com.abc;

public class Transaction {

    private final double amount;

    private final long transactionTime;

    protected static final DateProvider dateProvider = DateProvider.getInstance();

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionTime = dateProvider.now();
    }

    public double getAmount() {
        return amount;
    }

    public long getTransactionTime() {
        return transactionTime;
    }
}
