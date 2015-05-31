package com.abc;

public class WithdrawalTransaction extends AbstractTransaction {

    public WithdrawalTransaction(double amount) {
        super("withdrawal", -amount);
    }
}
