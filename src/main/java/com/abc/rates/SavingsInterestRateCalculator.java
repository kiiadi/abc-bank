package com.abc.rates;

public class SavingsInterestRateCalculator implements InterestRateCalculator {
    @Override
    public double calculate(double amount) {
        if (amount <= 1000)
            return amount * 0.001;
        else
            return 1 + (amount - 1000) * 0.002;
    }
}
