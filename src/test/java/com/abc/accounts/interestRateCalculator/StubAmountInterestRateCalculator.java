package com.abc.accounts.interestRateCalculator;

public class StubAmountInterestRateCalculator implements InterestRateCalculator<Double> {

    @Override
    public double calculateInterestRate(Double amount) {
        if (amount > 1000)
            return 0.01;
        else
            return 0.001;
    }
}
