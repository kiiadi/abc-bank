package com.abc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;
    /** current balance in the account*/
    private double balance;
    /** date account opened*/
    private Date start_date;

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.start_date = DateProvider.getInstance().now();
        this.transactions = new ArrayList<Transaction>();
        balance = 0;
    }
    
    public Account(int accountType, Date start_date) {
        this.accountType = accountType;
        this.start_date = start_date;
        this.transactions = new ArrayList<Transaction>();
        balance = 0;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
            balance += amount;
        }
    }

public void withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } else {
        transactions.add(new Transaction(-amount));
        balance -= amount;
    }
}

    public double interestEarned() {
    	long account_open_duration = Calendar.getInstance().getTimeInMillis() - start_date.getTime();
    	// number of days account was open
    	int account_open_days = (int) (account_open_duration / (1000*60*60*24));
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * (0.001)*(account_open_days/365);
                else
                    return 1 + (amount-1000) * (0.002)*(account_open_days/365);
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;
            case MAXI_SAVINGS:
            	Transaction last_transaction = transactions.get(transactions.size()-1);
            	long duration = last_transaction.getTransactionDate().getTime();
            	// checks time between now and last transaction
            	long time_gap = Calendar.getInstance().getTimeInMillis() - duration;
            	// if time_gap is more than 10, rate is 5%
            	if(10 < (int) (time_gap / (1000*60*60*24))){
            		return amount * (0.05)*(account_open_days/365);
            	}
                return amount * 0.01*(account_open_days/365);
            default:
                return amount * 0.001*(account_open_days/365);
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

    public double getBalance(){
    	return balance;
    }
    
    public void setTransactionList(List<Transaction> transactions){
    	this.transactions = transactions;
    }
}
