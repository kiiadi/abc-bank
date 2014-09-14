package com.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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

	public void withdraw(double amount) {
	    if (amount <= 0) {
	        throw new IllegalArgumentException("amount must be greater than zero");
	    } else {
	        transactions.add(new Transaction(-amount));
	    }
	}

    public double interestEarned() {
        double amount = sumTransactions();
        boolean withdrawalsExist = checkIfWithdrawalsExist(10);
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return (amount * 0.001) / 365;
                else
                    return (1/365) + (((amount-1000) * 0.002)/365);
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;
            case MAXI_SAVINGS:
            	if (!withdrawalsExist) 
            		return amount * 0.05 / 365;
                return amount * 0.1 / 365;
            default:
                return amount * 0.001 / 365;
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
    
    public boolean checkIfWithdrawalsExist(int days) {
    	
    	final Date now = DateProvider.getInstance().now();
    	for (Transaction t: transactions) {
    		long duration = (t.getTransactionDate().getTime() -  now.getTime()) /  (24 * 60 * 60 * 1000);
    		if ((duration < days) && (t.amount < 0))
    			return true;
    	}
    	return false;
    }
}
