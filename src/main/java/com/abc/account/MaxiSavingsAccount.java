package com.abc.account;

import java.util.Calendar;
import java.util.Date;

import com.abc.transaction.Transaction;
import com.abc.transaction.WithdrawalTransaction;

public class MaxiSavingsAccount extends AbstractAccount {
    public MaxiSavingsAccount() {
        super("Maxi Savings");
    }

    public double interestEarned() {
        double rate = isAnyWithdrawalAfter(daysAgo(10)) ? 0.001 : 0.05;
        return getBalance() * rate;
    }

    private boolean isAnyWithdrawalAfter(Date date) {
        for (Transaction transaction : getTransactions()) {
            if (transaction instanceof WithdrawalTransaction
                    && transaction.getDate().after(date)) {
                return true;
            }
        }
        return false;
    }

    private Date daysAgo(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -days);
        return cal.getTime();
    }
}
