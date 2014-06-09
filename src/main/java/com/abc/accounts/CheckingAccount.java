package com.abc.accounts;

public class CheckingAccount extends AbstractAccount {

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


