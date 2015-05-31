package com.abc.account;

public class SavingsAccount extends AbstractAccount {

    private static final InterestCalculator TIER_1_INTEREST = new InterestCalculator(0.1);
    private static final InterestCalculator TIER_2_INTEREST = new InterestCalculator(0.2);

    public SavingsAccount() {
        super("Savings");
    }

    public double interestEarned() {
        double amount = getBalance();
        if (amount <= 1000) {
            return TIER_1_INTEREST.calculate(amount);
        } else {
            return 1 + TIER_2_INTEREST.calculate(amount - 1000);
        }
    }
}
