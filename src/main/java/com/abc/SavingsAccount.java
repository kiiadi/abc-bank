package com.abc;

public class SavingsAccount extends Account {
    public SavingsAccount(int accountType) {
        super(accountType);
    }

    public double calculateInterest() {
        double acctBalance = getAccountBalance();
        if (acctBalance <= 1000)
            return acctBalance * 0.001;
        else
            return 1 + (acctBalance - 1000) * 0.002;
    }
}
