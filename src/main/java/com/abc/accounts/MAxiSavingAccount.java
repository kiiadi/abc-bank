package com.abc.accounts;

import com.abc.accounts.interestRateCalculator.InterestRateCalculator;

public class MaxiSavingAccount extends AbstractAccount {

    private InterestRateCalculator interestRateCalculator;

    public MaxiSavingAccount(InterestRateCalculator interestRateCalculator) {

        this.interestRateCalculator = interestRateCalculator;
    }

    @Override
    public double interestEarned() {
        double amount = sumTransactions();
        double interestEarned = amount * interestRateCalculator.calculateInterestRate(getTransactions(), amount);
        return interestEarned;
    }

    @Override
    public String getAccountDescription() {
        return "Maxi Savings Account";
    }
}


