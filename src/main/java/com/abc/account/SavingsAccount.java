package com.abc.account;

public class SavingsAccount extends AbstractAccount {
    public SavingsAccount() {
        super("Savings");
    }

    public double interestEarned() {
        double amount = getBalance();
        if (amount <= 1000) {
            return amount * 0.001;
        } else {
            return 1 + (amount - 1000) * 0.002;
        }
    }
}
