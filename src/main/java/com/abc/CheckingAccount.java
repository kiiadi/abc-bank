package com.abc;

/**
 * Checking account implementation of the Acount object
 */
public class CheckingAccount extends Account{

    /**
     * Instantiates the checking account object
     * @param accountType the type of account
     */
    public CheckingAccount(int accountType) {
        super(accountType);
    }

    /**
     * Calculates the interest for the checking account.
     * Checking accounts have a flat interest rate of 0.1% accrued daily
     * @return
     */
    public double calculateInterest() {
        return (getAccountBalance() * 0.001) * (getNumOfDaysForInterestAccrual() / 365);
    }
}
