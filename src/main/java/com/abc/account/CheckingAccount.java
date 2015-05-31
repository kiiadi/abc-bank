package com.abc.account;

public class CheckingAccount extends AbstractAccount {
    public CheckingAccount() {
        super("Checking");
    }

    public double interestEarned() {
        return getBalance() * 0.001;
    }
}
