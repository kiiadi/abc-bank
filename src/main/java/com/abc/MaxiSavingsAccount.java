package com.abc;

import java.util.Calendar;

/**
 * The Maxi-Savings account implementation of the account
 */
public class MaxiSavingsAccount extends Account{
    /**
     * Instantiates the maxi savings account object
     * @param accountType the type of the account
     */
    public MaxiSavingsAccount(int accountType) {
        super(accountType);
    }

    /**
     * Calculates the interest in a maxi-savings account.
     * Maxi-savings account have an interest rate of 5% assuming no withdrawals in the last 10 days, otherwise 0.1%
     * The interest rate is accrued daily
     * @return
     */
    public double calculateInterest() {
        double acctBalance = getAccountBalance();
        Transaction lastWithdrawal = getLastWithdrawalTransaction();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());
        calendar.add(Calendar.DATE, -10);
        if (lastWithdrawal != null && lastWithdrawal.getTransactionDate().after(calendar.getTime())) {
            return (acctBalance * 0.001) * (getNumOfDaysForInterestAccrual() / 365);
        }
        else {
            return (acctBalance * 0.05) * (getNumOfDaysForInterestAccrual() / 365);
        }
    }
}
