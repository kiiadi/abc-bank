package com.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Account {

    /* Add id for the account - simple incrementing integer */
    public static int UID  = 0;

    private int accountId;
    private AccountBalance accountBalance;
    private double totalInterest;
    public List<Transaction> transactions;
    /* Add reentrant lock for thread safety */
    private final ReentrantLock lock = new ReentrantLock();

    public Account() {
        setAccountId();
        this.transactions = new ArrayList<Transaction>();
        this.accountBalance = new AccountBalance();
        this.totalInterest=0;
    }
    
    /* Add a simple inner class encapsulating accountBalance and the date of this balance */
    class AccountBalance {
        Date date;
        double balance;
        
        AccountBalance () {
            setBalance(0.0,DateProvider.getInstance().now());
        }
        
        void setBalance(double amount, Date transDate) {
            balance = amount;
            date = transDate;
        }
    }
    
    /* Current balance is the most recent balance plus interest earned */ 
    private double currentBalance() {
        Date currentDate = DateProvider.getInstance().now();
        return currentBalance(currentDate);
     }
    
     
    private double currentBalance(Date date) {
        Date pastDate = accountBalance.date;
        int daysBetween =  (int)( (date.getTime() - pastDate.getTime()) / (1000 * 60 * 60 * 24)); // Milliseconds in a day
        double balance = accountBalance.balance;
        
        /* Increment interest daily in case an interest rate threshold is breached and the rate increases */
        for (int i=1; i <= daysBetween;i++) {
        	double newInterest = interestEarned(balance,i);
        	balance += newInterest;
            this.totalInterest += newInterest;
        }
        return balance;
     }

    public void deposit(double amount, Date date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            lock.lock();
            try {
                transactions.add(new Transaction(amount,date));
                accountBalance.setBalance(currentBalance()+amount, date);
            } finally {
                lock.unlock();
            }
        }
    }
    
    public void deposit(double amount) {
        Date currentDate = DateProvider.getInstance().now();
        deposit(amount, currentDate);
    }

    public void withdraw(double amount, Date date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            lock.lock();
            try {
                double balance=currentBalance();
                if (amount > balance) {
                    throw new IllegalArgumentException("insufficient funds");
                } else {
                    transactions.add(new Transaction(-amount, date));
                    accountBalance.setBalance(balance-amount,date);
                }
            } finally {
                lock.unlock();
            }
        }
    }    
    
    public void withdraw(double amount) {
        Date currentDate = DateProvider.getInstance().now();
        withdraw(amount, currentDate);
    }
    
    /* Replacement function showing interest accruing daily */
    protected abstract double interestEarned(double amount, int days);

   
    private synchronized void setAccountId () {
        accountId=UID++;
    }
    
    public int getAccountId () {
        return accountId;
    }
    
    public void transferFunds(Account destination, double amount) {
        this.withdraw(amount);
        destination.deposit(amount);
    }

    public double getTotalInterest () {
        return totalInterest;
    }
    
    public double getCurrentBalance() {
        accountBalance.setBalance(currentBalance(), DateProvider.getInstance().now());
        return accountBalance.balance;
    }
    
    public abstract String getDescription();
    
}
