package com.abc.interestRateCalculators;

import com.abc.Transaction;

import java.util.List;

public class StubInterestRateCalculator implements InterestRateCalculator<List<Transaction>> {
    @Override
    public double calculateInterestRate(List<Transaction> transactions) {
        return 0.001;
    }
}
