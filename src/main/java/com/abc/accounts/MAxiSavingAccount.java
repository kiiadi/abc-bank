package com.abc.accounts;

import com.abc.Account;

public class MaxiSavingAccount extends Account {

    public double interestEarned() {
        double amount = sumTransactions();
        if (amount <= 1000)
            return amount * 0.02;
        if (amount <= 2000)
            return 20 + (amount - 1000) * 0.05;

        return 70 + (amount - 2000) * 0.1;
    }

    @Override
    public String getAccountDescription() {
        return "Maxi Savings Account";
    }
}


