package com.abc.accounts;

import com.abc.accounts.interestRateCalculator.InterestRateCalculator;

public class SavingAccount extends AbstractAccount {


    private InterestRateCalculator interestRateCalculator;

    public SavingAccount(InterestRateCalculator interestRateCalculator) {
        this.interestRateCalculator = interestRateCalculator;
    }

    @Override
    public double interestEarned() {
        double amount = sumTransactions();
        double interestRate = interestRateCalculator.calculateInterestRate(getTransactions(), amount);

        if (amount <= 1000)
            return amount * interestRate;
        else
            return inerestForFirst1000() + (amount - 1000) * interestRate;

    }

    private double inerestForFirst1000() {
        return 1000*interestRateCalculator.calculateInterestRate(getTransactions(), 1000);
    }

    @Override
    public String getAccountDescription() {
        return "Savings Account";
    }

}
