package com.abc;

public enum AccountType {
    SAVINGS_ACCOUNT(new SavingsAccountInterestCalculator()),
    CHECKING_ACCOUNT(new CheckingAccountInterestCalculator()),
    MAXISAVINGS_ACCOUNT(new MaxiSavingsAccountInterestCalculator());

    InterestCalculator interestCalculator;

    AccountType(InterestCalculator interestCalculator) {
        this.interestCalculator = interestCalculator;
    }

    public double calculateInterest(double balance) {
        return interestCalculator.interestEarned(balance);
    }
}

