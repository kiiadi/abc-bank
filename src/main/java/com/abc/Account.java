package com.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;
    /* Add id for the account - simple imcrementing integer */
    public static int UID  = 0;

    private final int accountType;
    private int accountId;
    private AccountBalance accountBalance;
    private double totalInterest;
    public List<Transaction> transactions;
    /* Add reentrant lock for thread safety */
    private final ReentrantLock lock = new ReentrantLock();

    public Account(int accountType) {
        setAccountId();
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
        this.accountBalance = new AccountBalance();
        this.totalInterest=0;
    }
    
    /* Add a simple inner class encapsulating accountBalance and the date of this balance */
    class AccountBalance {
        Date date;
        double balance;
        
        AccountBalance () {
            setBalance(0.0);
        }
        
        setBalance(double balance) {
            balance = balance;
            date = DateProvider.getInstance().now();
        }
    }
    
    /* Current balance is the most recent balance plus interest earned */ 
    private double currentBalance() {
        Date currentdate = DateProvider.getInstance().now();
        Date pastDate = accountBalance.date;
        int daysBetween =  (int)( (currentDate.getTime() - pastDate.getTime()) / (1000 * 60 * 60 * 24));
        double pastBalance = accountBalance.balance;
        double newInterest = interestEarned(pastBalance,daysBetween);
        this.totalInterest += newInterest;
        return pastBalance + newInterest;
     }

    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            lock.lock();
            try {
                transactions.add(new Transaction(amount));
                accountBalance.setBalance(currentBalance()+amount);
            } finally {
                lock.unlock();
            }
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            lock.lock();
            try {
                balance=currentBalance();
                if (amount > balance) {
                    throw new IllegalArgumentException("insufficient funds");
                } else {
                    transactions.add(new Transaction(-amount));
                    accountBalance.setBalance(balance-amount);
                }
            } finally {
                lock.unlock();
            }
        }
    }
    /* Replacement function showing interest accruing daily */
    public double interestEarned(double amount, int days) {
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001 * (double) days / 365;
                else
                    return (1 + (amount-1000) * 0.002) * (double) days / 365;
            case MAXI_SAVINGS:
                if (amount <= 1000)
                    return (amount * 0.02) * (double) days / 365;
                if (amount <= 2000)
                    return (20 + (amount-1000) * 0.05) * (double) days / 365;
                return (70 + (amount-2000) * 0.1) * (double) days / 365;
            default:
                return (amount * 0.001) * (double) days / 365;
        }
    }
    
    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;
            case MAXI_SAVINGS:
                if (amount <= 1000)
                    return amount * 0.02;
                if (amount <= 2000)
                    return 20 + (amount-1000) * 0.05;
                return 70 + (amount-2000) * 0.1;
            default:
                return amount * 0.001;
        }
    }
    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }
    
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
}
