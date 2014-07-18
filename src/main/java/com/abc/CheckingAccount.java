package com.abc;

/**
 * Class represents the checking account implementation.
 */
public class CheckingAccount extends Account {

    private static final String ACCOUNT_NAME = "Checking Account";

    @Override
    public double interestEarned() {
        return sumTransactions() * 0.001;
    }

    @Override
    public String getAccountName() {
        return ACCOUNT_NAME;
    }
}
