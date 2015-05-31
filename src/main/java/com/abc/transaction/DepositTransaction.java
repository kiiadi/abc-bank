package com.abc.transaction;

import com.abc.AbstractTransaction;

public class DepositTransaction extends AbstractTransaction {

    public DepositTransaction(double amount) {
        super("deposit", amount);
    }

    public double applyTo(double balance) {
        return balance + amount;
    }
}
