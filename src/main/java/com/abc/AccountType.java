package com.abc;


import com.abc.rates.FlatInterestRateCalculator;
import com.abc.rates.InterestRateCalculator;
import com.abc.rates.MaxiSavingsInterestRateCalculator;
import com.abc.rates.SavingsInterestRateCalculator;

public enum AccountType {
    Checking(new FlatInterestRateCalculator(0.001)),
    Savings(new SavingsInterestRateCalculator()),
    MaxiSavings(new MaxiSavingsInterestRateCalculator()){
        @Override
        public String toString() {
            return "Maxi Savings";
        }
    };

    public final InterestRateCalculator calculator;
    AccountType(InterestRateCalculator calculator) {
        this.calculator = calculator;
    }

    double calculateInterestEarned(final double amount) {
        return calculator.calculate(amount);
    }
}
