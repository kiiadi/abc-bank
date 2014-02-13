package com.abc;


public class SavingsAccountInterestCalculator implements InterestCalculator {
    @Override
    public double interestEarned(double amount) {

        double firstThousandInterest = (amount * Math.pow((1 + (0.001 / 365)), 365)) - amount;
        if (amount <= 1000)
            return firstThousandInterest;
        else
            return firstThousandInterest + (((amount - 1000) * Math.pow((1 + (0.002 / 365)), 365)) - (amount - 1000));


    }
}
