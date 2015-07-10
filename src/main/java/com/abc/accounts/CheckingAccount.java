package com.abc.accounts;


public class CheckingAccount extends Account {

    @Override
    public double interestEarned() {
        double amount = sumTransactions();
        return amount * 0.001;
    }

    @Override
    public String accountTypeString() {
        return "Checking Account";
    }
}
