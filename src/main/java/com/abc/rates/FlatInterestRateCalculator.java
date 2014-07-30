package com.abc.rates;

public class FlatInterestRateCalculator implements InterestRateCalculator {
    private double rate;

    public FlatInterestRateCalculator(final double rate) {
        this.rate = rate;
    }

    public double calculate(double amount) {
        return amount * rate;
    }
}