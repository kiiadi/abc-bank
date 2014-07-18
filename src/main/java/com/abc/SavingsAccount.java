package com.abc;

/**
 * Class represents the savings account implementation.
 */
public class SavingsAccount extends Account {

    private static final String ACCOUNT_NAME = "Savings Account";

    @Override
    public double interestEarned() {
        double amount = sumTransactions();
        if (amount <= 1000)
            return amount * 0.001;
        else
            return 1 + (amount - 1000) * 0.002;
    }

    @Override
    public String getAccountName() {
        return ACCOUNT_NAME;
    }
}
