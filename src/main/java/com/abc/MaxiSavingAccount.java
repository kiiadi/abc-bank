package com.abc;


import java.math.BigDecimal;
import java.util.Calendar;

public class MaxiSavingAccount extends Account {

    public BigDecimal interestEarned() {
        BigDecimal amount = getBalance();

        //interest rate of 5% assuming no withdrawals in the past 10 days otherwise 0.1%
        Transaction lastWithdrawal = findLastWithdrawal();
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateProvider.getInstance().now());
        cal.add(Calendar.DATE, -10); // subtract 10 days

        if (lastWithdrawal != null && lastWithdrawal.getTransactionDate().after(cal.getTime())) {
            return amount.multiply(BigDecimal.valueOf(0.001));
        } else {
            return amount.multiply(BigDecimal.valueOf(0.05));
        }
    }

    public String getLabel() {
        return "Maxi Savings Account";
    }

}
