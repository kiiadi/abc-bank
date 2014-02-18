package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

    private static long accNum;
    public static final int CHECKING = 1;
    public static final int SAVINGS = 2;
    public static final int MAXI_SAVINGS = 3;

    private final int accountType;
    private final long accountNumber;
    private List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
        this.accountNumber = generateAccountNumber();
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
        } else
            if(getAccountBalance() < amount){
                throw new IllegalArgumentException("insufficient amount...");
            } else {
                transactions.add(new Transaction(-amount));
        }
    }

    public double interestEarned() {
        double interest=0.0;
        double amount=0.0;
        Date fromDate=null;
        Date toDate=null;
        Date withdrawalDate = null;
        Transaction t;
        java.util.Iterator<Transaction> itr=transactions.iterator();
        if(itr.hasNext()){
            t=itr.next(); amount=t.amount;
            fromDate=t.getTransactionDate(); withdrawalDate=t.getTransactionDate();
            while(itr.hasNext()){
                t=itr.next(); toDate=t.getTransactionDate();
                interest += calculateInterest(amount, fromDate, toDate, withdrawalDate);
                fromDate=toDate; amount+=t.amount;
                if(t.amount<0) withdrawalDate=toDate;
            }
            toDate=DateProvider.getInstance().now();
            interest += calculateInterest(amount, fromDate, toDate, withdrawalDate);
        }
        return Math.round(interest*100.0)/100.0;
    }

    private double sumTransactions() {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

    public double getAccountBalance(){
        return sumTransactions() + interestEarned();
    }

    private double getRate(int numDays, double rate){
        return rate / 365 *numDays;
    }

    private double calculateInterest(double amount, Date fromDate, Date toDate, Date withdrawalDate){
        int numDays=(int)((toDate.getTime() - fromDate.getTime())/(1000*60*60*24));
        amount=Math.abs(amount);
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * getRate(numDays, 0.001);
                else
                    return 1000 * getRate(numDays, 0.001) + (amount-1000) * getRate(numDays, 0.002);
            case MAXI_SAVINGS:
// New Maxi Logic
                if((int)((toDate.getTime() - withdrawalDate.getTime())/(1000*60*60*24)) >= 10)
                    return amount * getRate(numDays, 0.05);
                else
                    return amount * getRate(numDays, 0.001);
//Old Maxi logic
//                if (amount <= 1000)
//                    return amount * getRate(numDays, 0.02);
//                if (amount <= 2000)
//                    return 1000 * getRate(numDays, 0.02) + (amount-1000) * getRate(numDays, 0.05);
//                return 1000 * getRate(numDays, 0.02) + 1000 * getRate(numDays, 0.05) + (amount-2000) * getRate(numDays, 0.1);
            default:
                return amount * getRate(numDays, 0.001);
        }
    }

    private synchronized long generateAccountNumber(){
        // in addition to the static accNum it maybe a good idea
        // to use Customer's name hascode or something pulled from a db
        // fro now we'll just increment this static number
        return accNum++;
    }

    public long getAccountNumber(){
        return this.accountNumber;
    }

    public List<Transaction> transactions(){
        return this.transactions;
    }
}
