package com.abc.accounts.interestRateCalculator;

import com.abc.Transaction;

import java.util.List;

public class StubInterestRateCalculator implements InterestRateCalculator {
    @Override
    public double calculateInterestRate(List<Transaction> transactions, double totalAmount) {
        return 0.001;
    }
}
