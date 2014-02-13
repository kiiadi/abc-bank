package com.abc;

public class MaxiSavingsAccountInterestCalculator implements InterestCalculator {
    @Override
    public double interestEarned(double amount) {
        double interestEarned = 0.0;
        double firstThousandInterest = (amount * Math.pow((1 + (0.02 / 365)), 365)) - amount;
        double secondThousandInterest = firstThousandInterest + (((amount - 1000) * Math.pow((1 + (0.05 / 365)), 365)) - (amount - 1000));

        if (amount <= 1000)
            interestEarned = firstThousandInterest;
        else if (amount <= 2000)
            interestEarned = firstThousandInterest + secondThousandInterest;
        else
            interestEarned = firstThousandInterest + secondThousandInterest + (((amount - 2000) * Math.pow((1 + (0.1 / 365)), 365)) - (amount - 2000));

        return interestEarned;
    }


}
