package com.abc.accounts;

import com.abc.Account;

public class CheckingAccount extends Account {

    @Override
    public double interestEarned() {
        double amount = sumTransactions();
        return amount * 0.001;
    }

    @Override
    public String getAccountDescription() {
        return "Checking Account";
    }


}


