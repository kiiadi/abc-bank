package com.abc;

/**
 * The savings account implementation of the account
 */
public class SavingsAccount extends Account {
    /**
     * Instantiates the savings account object
     * @param accountType the type of account
     */
    public SavingsAccount(int accountType) {
        super(accountType);
    }

    /**
     * Calculates the interest for the savings account.
     * Savings accounts have a rate of 0.1% for the first $1,000 then 0.2%
     * @return the interest for the savings account
     */
    public double calculateInterest() {
        double acctBalance = getAccountBalance();
        if (acctBalance <= 1000)
            return (acctBalance * 0.001) * (getNumOfDaysForInterestAccrual() / 365);
        else
            return 1 + ((acctBalance - 1000) * 0.002) * (getNumOfDaysForInterestAccrual() / 365);
    }
}
