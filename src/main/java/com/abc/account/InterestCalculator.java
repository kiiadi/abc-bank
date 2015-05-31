package com.abc.account;

public class InterestCalculator {

    private final double rate;

    public InterestCalculator(double percentageRate) {
        rate = percentageRate / 100;
    }

    public double calculate(double amount) {
        return amount * rate;
    }
}
