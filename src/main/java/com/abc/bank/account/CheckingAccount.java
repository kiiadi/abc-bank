package com.abc.bank.account;

/**
 * Checking accounts have a flat interest rate of 0.1%
 *
 */
class CheckingAccount extends Account{

    @Override
    public double interestEarned() {
        return getBalance() * 0.001;
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.checking;
    }
}
