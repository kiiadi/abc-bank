package com.abc.bank.account;

/**
 * Savings accounts have a rate of 0.1% for the first $1,000 then 0.2%
 *
 */
class SavingsAccount extends Account{

    @Override
    public double interestEarned() {
        double amount = getBalance();
        if (amount <= 1000)
            return amount * 0.001;
        else
            return 1 + (amount-1000) * 0.002;
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.savings;
    }
}
