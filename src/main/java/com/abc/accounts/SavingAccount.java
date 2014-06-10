package com.abc.accounts;

import com.abc.interestRateCalculators.InterestRateCalculator;

public class SavingAccount extends AbstractAccount {


    private InterestRateCalculator<Double> interestRateCalculator;

    public SavingAccount(InterestRateCalculator interestRateCalculator) {
        this.interestRateCalculator = interestRateCalculator;
    }

    @Override
    public double interestEarned() {
        double amount = sumTransactions();
        double interestRate = interestRateCalculator.calculateInterestRate(amount);

        if (amount <= 1000)
            return amount * interestRate;
        else
            return inerestForFirst1000() + (amount - 1000) * interestRate;

    }

    private double inerestForFirst1000() {
        return 1000*interestRateCalculator.calculateInterestRate(1000.00);
    }

    @Override
    public String getAccountDescription() {
        return "Savings Account";
    }

}
