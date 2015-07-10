package com.abc.accounts;



public class CheckingAccount extends Account {

    final static double ANNUAL_RATE = 0.001;
    
    @Override
    public String accountTypeString() {
        return "Checking Account";
    }

    @Override
    public double getAnnualRateForBalance(double balance) {
        return ANNUAL_RATE;
    }
}
