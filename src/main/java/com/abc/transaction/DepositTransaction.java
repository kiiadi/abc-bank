package com.abc.transaction;

public class DepositTransaction extends AbstractTransaction {

    public DepositTransaction(double amount) {
        super("deposit", amount);
    }

    public double applyTo(double balance) {
        return balance + amount;
    }
}
