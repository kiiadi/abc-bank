package com.abc.accounts;

import com.abc.Transaction;
import com.abc.accounts.interestRateCalculator.InterestRateCalculator;

import java.util.List;

public class CheckingAccount extends AbstractAccount {

    private InterestRateCalculator<List<Transaction>> interestRateCalculator;

    public CheckingAccount(InterestRateCalculator interestRateCalculator) {
        this.interestRateCalculator = interestRateCalculator;
    }

    @Override
    public double interestEarned() {
        double amount = sumTransactions();
        return amount * interestRateCalculator.calculateInterestRate(getTransactions());
    }

    @Override
    public String getAccountDescription() {
        return "Checking Account";
    }


}


