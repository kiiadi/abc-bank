package com.abc.accounts;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.abc.DateProvider;
import com.abc.Transaction;

public abstract class Account {

    public List<Transaction> transactions;

    public Account() {
        this.transactions = new Vector<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0 || amount > sumTransactions()) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(-amount));
        }
    }


    public double sumTransactions() {
        return transactions.stream().mapToDouble(t->t.amount).sum();
    }

    boolean hasWithdrawlInLast10Days() {
        Date daysAgo = DateProvider.daysAgo(10);
        return transactions.stream().filter( t -> (t.isWithdrawal() && t.getTransactionDate().after(daysAgo))).count() > 0;
    }

    public abstract String accountTypeString();
    public abstract double getAnnualRateForBalance(double balance);
    
    public double interestEarned() {
        List<RunningBalance> runningBalance = getRunningBalance();
        
        return runningBalance.stream().mapToDouble( r ->  calculateInterest(r) ).sum();
    }

    
    protected double calculateInterest(RunningBalance r) {
        double interest =  r.amount * Math.pow( (1 + getAnnualRateForBalance(r.amount)/365), Math.max(r.numberOfDays, 1)) - r.amount;
        return interest;
    }
    
    
    private List<RunningBalance> getRunningBalance(){
        
        AtomicReference<Transaction> prevTran = new AtomicReference<>();
        AtomicReference<RunningBalance> prevBal = new AtomicReference<>();
        return transactions.stream()
                .sorted((t1, t2) -> Long.compare(t1.getTransactionDate().getTime(),
                                                 t2.getTransactionDate().getTime()))
                .map(t -> createRunningBalance(prevTran, prevBal, t))
                .collect(Collectors.toList());
        
    }

    private RunningBalance createRunningBalance(AtomicReference<Transaction> prevTran, AtomicReference<RunningBalance> prevBal,
            Transaction t) {
        Transaction p = prevTran.getAndSet(t);
        RunningBalance prb = prevBal.get();
        RunningBalance current = null;
        if(prb == null) {
            current = new RunningBalance(t.getTransactionDate(), t.amount, daysBetween(new Date(), t.getTransactionDate()));
        } else {
            prb.numberOfDays = daysBetween(p.getTransactionDate(), t.getTransactionDate());
            current = new RunningBalance(t.getTransactionDate(), t.amount + prb.amount, daysBetween(new Date(), t.getTransactionDate()));
        }
        prevBal.set(current);
        return current;
    }
    
    private int daysBetween(Date from, Date to) {
        Calendar past = Calendar.getInstance();
        past.setTime(from);
        Calendar present = Calendar.getInstance();
        present.setTime(to);
        
        return present.get(Calendar.DAY_OF_YEAR) - past.get(Calendar.DAY_OF_YEAR);
    }
    
    // tracks the running balance of the account
    class RunningBalance{
        Date transactionDate;
        double amount;
        int numberOfDays;
        public RunningBalance(Date tDate, double amt, int numDays) {
            transactionDate = tDate;
            amount = amt;
            numberOfDays = numDays;
        }
        @Override
        public String toString() {
            return "RunningBalance [transactionDate=" + transactionDate + ", amount=" + amount + ", numberOfDays="
                    + numberOfDays + "]";
        }
        
    }

}
