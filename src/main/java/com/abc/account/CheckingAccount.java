package com.abc.account;

public class CheckingAccount extends AbstractAccount {

    private static final InterestCalculator INTEREST = new InterestCalculator(0.1);

    public CheckingAccount() {
        super("Checking");
    }

    public double interestEarned() {
        return INTEREST.calculate(getBalance());
    }
}
