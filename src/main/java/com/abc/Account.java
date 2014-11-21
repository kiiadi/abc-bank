package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final String accountNumber;
    private final int accountType;
    private List<Transaction> transactions;  // Henry made it to private

    public Account(int accountType, String accountNumber) {
    	this.accountNumber = accountNumber; 
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    //henry added synchronized
    public synchronized void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }
    
    // Henry added this method and return a copy of the transactions, which is ok since transaction has a public amount only.
	public List<Transaction> getTransactions() {
		return new ArrayList<Transaction>(transactions);
	}

	//henry added synchronized
	public synchronized void  withdraw(double amount) {
	    if (amount <= 0) {
	        throw new IllegalArgumentException("amount must be greater than zero");
	    } else {
	    	if(!hasFund(amount))
	    		throw new IllegalArgumentException("Not enough fund to withdraw.");
	        transactions.add(new Transaction(-amount));
	    }
	}
	
	 private boolean hasFund(double amount){
	    	return this.sumTransactions() > amount;
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

	public String getAccountNumber() {
		return accountNumber;
	}

}
