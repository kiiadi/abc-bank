package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.abc.utils.BankUtils;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    private List<Transaction> transactions;

	private final ReentrantReadWriteLock transactionsLock = new ReentrantReadWriteLock();

	public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
    	this.depositAsOf(amount, DateProvider.getInstance().now());
    }

    public void depositAsOf(double amount, Date asOfDate) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
        	try {
        		transactionsLock.writeLock().lock();
        		transactions.add(new Transaction(amount, asOfDate));
        	} finally {
        		transactionsLock.writeLock().unlock();
        	}
        }
    }

public void withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } else {
    	try {
    		transactionsLock.writeLock().lock();
            transactions.add(new Transaction(-amount));
    	} finally {
    		transactionsLock.writeLock().unlock();
    	}
    }
}

    public double interestEarned() {
        double amount = 0;
        double intEarned = 0;
        int days = 0;
        Date prevTransactionDate = null;
        
        for(Transaction t : transactions){
        	amount += t.amount;
        	if (prevTransactionDate == null)
        		days = (int)((DateProvider.getInstance().now().getTime() - t.transactionDate.getTime()) / (1000 * 60 * 60 * 24));
        	else
        		days = (int)((t.transactionDate.getTime() - prevTransactionDate.getTime()) / (1000 * 60 * 60 * 24));

        	prevTransactionDate = t.transactionDate;
        			
            switch(accountType){
	            case SAVINGS:
	                if (amount <= 1000)
	                	intEarned += amount * 0.001 / 365 * days;
	                else
	                    return 1 + (amount-1000) * 0.002 / 365 * days;
	            break;
	            case MAXI_SAVINGS:
	                if (!withdrewInPast10Days())
	                	intEarned += amount * 0.05 / 365 * days;
	                else
	                	intEarned += amount * 0.0001 / 365 * days;
	            break;
	            default:
	            	intEarned += amount * 0.001 / 365 * days;
	        }
        }
        return intEarned;
    }

    private boolean withdrewInPast10Days() {
    	long tenDaysBefore = new Date().getTime() - (1000 * 60 * 60 * 24) * 10;
    	try {
    		transactionsLock.readLock().lock();
	        for (Transaction t: transactions){
	            if( t.transactionDate.getTime() > tenDaysBefore && t.amount < 0 ){
	            	return true;
	            }
	        }
    	} finally {
    		transactionsLock.readLock().unlock();
    	}
		return false;
	}

	public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
    	try {
    		transactionsLock.readLock().lock();
	        for (Transaction t: transactions)
	            amount += t.amount;
    	} finally {
    		transactionsLock.readLock().unlock();
    	}
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

    public String statementForAccount() {
        String s = "";

       //Translate to pretty account type
        switch(this.getAccountType()){
            case Account.CHECKING:
                s += "Checking Account\n";
                break;
            case Account.SAVINGS:
                s += "Savings Account\n";
                break;
            case Account.MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
    	try {
    		transactionsLock.readLock().lock();
	        for (Transaction t : transactions) {
	            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + BankUtils.toDollars(t.amount) + "\n";
	            total += t.amount;
	        }
    	} finally {
    		transactionsLock.readLock().unlock();
    	}
        s += "Total " + BankUtils.toDollars(total);
        return s;
    }

    public void transferTo(Account a, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }
    	try {
    		transactionsLock.writeLock().lock();
    		a.getTransactionsLock().writeLock().lock();
	        this.transactions.add(new Transaction(-amount));
	        a.transactions.add(new Transaction(amount));
    	} finally {
    		transactionsLock.writeLock().unlock();
    		a.getTransactionsLock().writeLock().unlock();
    	}
    }

    public ReentrantReadWriteLock getTransactionsLock() {
		return transactionsLock;
	}

}
