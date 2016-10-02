package com.abc;

import java.util.ArrayList;
import java.util.List;

//Use AccountType as key, no duplicate account type for the same customer
public class Account {

    private final AccountType accountType;   
    public List<Transaction> transactions;

    public Account(AccountType accountType) {
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
	    }else if(sumTransactions() < amount) {
	    	throw new IllegalArgumentException("withdraw amount is greater than account sum");
	    }else {
	        transactions.add(new Transaction(-amount));
	    }
	}

    public double interestEarned() {
    	
    	return accountType.interestEarned(transactions);
    }
    
    public double sumTransactions() {
        
         double amount = 0.0;
         for (Transaction t: transactions)
             amount += t.getAmount();
         return amount;
    }

    public AccountType getAccountType() {
        return accountType;
    }

}
