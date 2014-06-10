package com.abc.accounts;

import com.abc.accounts.interestRateCalculator.InterestRateCalculator;

public class CheckingAccount extends AbstractAccount {

    private InterestRateCalculator interestRateCalculator;

    public CheckingAccount(InterestRateCalculator interestRateCalculator) {
        this.interestRateCalculator = interestRateCalculator;
    }

    @Override
    public double interestEarned() {
        double amount = sumTransactions();
        return amount * interestRateCalculator.calculateInterestRate(getTransactions(), amount);
    }

    @Override
    public String getAccountDescription() {
        return "Checking Account";
    }


}


