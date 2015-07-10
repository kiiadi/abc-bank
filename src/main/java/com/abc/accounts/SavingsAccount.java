package com.abc.accounts;



public class SavingsAccount extends Account{

    final static double REGULAR_RATE = 0.2/100.0;
    final static double PENALTY_RATE = 0.1/100.0;
    
    @Override
    public double getAnnualRateForBalance(double balance) {
        if(balance <= 1000) {
            return PENALTY_RATE;
        } else {
            return REGULAR_RATE;
        }
    }
    
    @Override
    protected double calculateInterest(RunningBalance r) {
        if(r.amount <= 1000)
            return r.amount * Math.pow( (1 + getAnnualRateForBalance(r.amount)/365), Math.max(r.numberOfDays, 1)) - r.amount;
        else {
            double firstPart = 1000 * Math.pow( (1 + getAnnualRateForBalance(1000)/365), Math.max(r.numberOfDays, 1)) - 1000;
            double over = (r.amount - 1000);
            double secondPart = over * Math.pow( (1 + getAnnualRateForBalance(r.amount)/365), Math.max(r.numberOfDays, 1)) - over;
            return firstPart + secondPart;
        }
    }
    

    @Override
    public String accountTypeString() {
        return "Savings Account";
    }
}
