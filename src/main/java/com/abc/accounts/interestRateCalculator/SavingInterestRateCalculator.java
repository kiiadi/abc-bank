package com.abc.accounts.interestRateCalculator;

public class SavingInterestRateCalculator implements InterestRateCalculator<Double>{

    @Override
    public double calculateInterestRate(Double totalAmount) {
        if (totalAmount <= 1000)
            return 0.001;
        else
            return 0.002;
    }





}
