package com.abc;

import java.util.Calendar;

public class MaxiSavingsAccount extends Account{
    public MaxiSavingsAccount(int accountType) {
        super(accountType);
    }

    public double calculateInterest() {
        double acctBalance = getAccountBalance();
        Transaction lastWithdrawal = getLastWithdrawalTransaction();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateProvider.getInstance().now());
        calendar.add(Calendar.DATE, -10);
        if (lastWithdrawal != null && lastWithdrawal.getTransactionDate().after(calendar.getTime())) {
            return acctBalance * 0.001;
        }
        else {
            return acctBalance * 0.05;
        }
    }
}
