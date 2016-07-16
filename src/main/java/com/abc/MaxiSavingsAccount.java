package com.abc;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by dineshram on 2/28/15.
 */
public class MaxiSavingsAccount extends Account {

    private static final String ACCOUNT_NAME = "Maxi Savings Account";

    public MaxiSavingsAccount() {
        super();
    }

    @Override
    public BigDecimal interestEarned() {
        
        BigDecimal amount = sumTransactions();

        /*if(amount.compareTo(new BigDecimal(1000)) <=0 ) {
            return amount.multiply(new BigDecimal(0.002));
        }

        if(amount.compareTo(new BigDecimal(2000)) <=0 ) {
            return amount.subtract(new BigDecimal(1000)).multiply(new BigDecimal(0.05)).add(new BigDecimal(20));
        }

        return amount.subtract(new BigDecimal(2000)).multiply(new BigDecimal(0.1)).add(new BigDecimal(70));*/

        if(withdrawInLast10Days()) {
            return amount.multiply(new BigDecimal(0.001));
        } else {
            return amount.multiply(new BigDecimal(0.05));
        }

    }

    @Override
    public String getAccountName() {
        return ACCOUNT_NAME;
    }

    // Returns true if there is a withdraw in last 10days
    public boolean withdrawInLast10Days() {

        Collections.sort(transactions, new TransactionComparator());

        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.DATE, -10);
        for(int i = transactions.size()-1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);
            // transaction date must be greater than today - 10
            if(transaction.transactionDate.after(cal.getTime())) {
                if(transaction.withdrawFlag) {
                    return true;
                }
            } else {
                return false;
            }

        }

        return false;
    }

}
