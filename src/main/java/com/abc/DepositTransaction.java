package com.abc;

public class DepositTransaction extends AbstractTransaction {

    public DepositTransaction(double amount) {
        super("deposit", amount);
    }
}
