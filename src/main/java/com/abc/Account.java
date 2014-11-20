package com.abc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;

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

    public void deposit(double amount, int daysAgo) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount, daysAgo));
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }
        else {
            transactions.add(new Transaction(-amount));
        }
    }
    public void withdraw(double amount, int daysAgo) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(-amount, daysAgo));
        }
    }

    public double interestEarned() {
        return sumInterest();
    }

    public double annualInterest(double amount){
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
            case MAXI_SAVINGS:
                for(int i = transactions.size() -1; i>=0; i--){
                    Transaction trans = transactions.get(i);
                    if (trans.daysFromNow()<10){
                        if (trans.isWithdraw()){
                            return amount * 0.001;
                        }
                    }
                    else {
                        break;
                    }
                }
                return amount * 0.005;
            default:
                return amount * 0.001;
        }
    }

    public double sumTransactions() {
        double amount = 0.0;
        for (Transaction t: transactions) {
            amount += t.amount;
        }
        return amount;
    }



    public double sumInterest() {
        double amount = 0.0;
        for (Transaction t: transactions) {
            amount += annualInterest(t.amount) * t.daysFromNow() / 365;
        }
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

}
