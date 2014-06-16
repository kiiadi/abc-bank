package com.abc.accounts;

import com.abc.Transaction;
import com.abc.interestRateCalculators.InterestRateCalculator;

import java.util.List;

public class MaxiSavingAccount extends AbstractAccount {

    private InterestRateCalculator<List<Transaction>> interestRateCalculator;

    public MaxiSavingAccount(InterestRateCalculator interestRateCalculator) {

        this.interestRateCalculator = interestRateCalculator;
    }

    @Override
    public double interestEarned() {
        double amount = sumTransactions();
        double interestEarned = amount * interestRateCalculator.calculateInterestRate(getTransactions());
        return interestEarned;
    }


    public double interestEarnedDaily() {
        double amount = sumTransactions();
        double interestEarnedPerYear = amount * interestRateCalculator.calculateInterestRate( getTransactions());

        double interestPerDay = interestEarnedPerYear / 365;

        return interestEarnedPerYear;
    }

    @Override
    public String getAccountDescription() {
        return "Maxi Savings Account";
    }
}


