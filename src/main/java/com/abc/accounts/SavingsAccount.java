package com.abc.accounts;


public class SavingsAccount extends Account{

    @Override
    public double interestEarned() {
        double amount = sumTransactions();
        if (amount <= 1000)
            return amount * 0.001;
        else
            return 1 + (amount - 1000) * 0.002;
    }


    @Override
    public String accountTypeString() {
        return "Savings Account";
    }
}
