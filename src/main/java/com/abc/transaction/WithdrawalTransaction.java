package com.abc.transaction;

public class WithdrawalTransaction extends AbstractTransaction {

    public WithdrawalTransaction(double amount) {
        super("withdrawal", amount);
    }

    public double applyTo(double balance) {
        return balance - getAmount();
    }
}
