package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    private List<Transaction> transactions;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(-amount));
        }
    }

       private Long dateDifference(Date firstDate, Date lastDate) {

        Long diff = null;
        final long ONE_HOUR = 60 * 60 * 1000L;

        if (firstDate != null && lastDate != null) {
            diff = (lastDate.getTime() - firstDate.getTime() + ONE_HOUR)
                    / (ONE_HOUR * 24);
        }

        return diff;
    }
       
    public boolean isWithdrawnRecently(int dayLimit){
        
        boolean recentWithdraw = false;
        
        Long days = null;
        Date now = DateProvider.getInstance().now();
        for(Transaction transaction : transactions){
            if(transaction.getAmount() < 0){
                days = dateDifference(transaction.getTransactionDate(), now);
            }
            if(days != null && days <= dayLimit)
                recentWithdraw = true;
        }
        return recentWithdraw;
    }
    public double interestEarned() {
        double amount = sumTransactions();
        switch (accountType) {
            case SAVINGS:
                if (amount <= 1000) {
                    return amount * 0.001;
                } else {
                    return 1 + (amount - 1000) * 0.002;
                }

            case MAXI_SAVINGS:
                if (amount <= 1000) {
                    return amount * 0.02;
                }
                if (amount <= 2000) {
                    return 20 + (amount - 1000) * 0.05;
                }
                return 70 + (amount - 2000) * 0.1;
            default:
                return amount * 0.001;
        }
    }

    public double sumTransactions() {
        return checkIfTransactionsExist();
    }

    private double checkIfTransactionsExist() {
        double amount = 0.0;
        for (Transaction t : transactions) {
            amount += t.getAmount();
        }
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

}
