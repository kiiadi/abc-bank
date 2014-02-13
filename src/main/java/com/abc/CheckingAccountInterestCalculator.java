package com.abc;

public class CheckingAccountInterestCalculator implements InterestCalculator {
    @Override
    public double interestEarned(double amount) {
        return (amount * Math.pow((1 + (0.001 / 365)), 365)) - amount;
    }
}
