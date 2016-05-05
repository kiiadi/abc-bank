package com.abc;

public class CheckingAccount extends Account{

    public CheckingAccount(int accountType) {
        super(accountType);
    }

    public double calculateInterest() {
        return getAccountBalance() * 0.001;
    }
}
