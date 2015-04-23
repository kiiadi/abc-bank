package com.abc;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class Account {

	public static final int CHECKING = 0;
	public static final int SAVINGS = 1;
	public static final int MAXI_SAVINGS = 2;
	
	private final int accountType;
	private List<Transaction> transactions;
	private double accountBalance;

	public Account(int accountType) {
		this.accountType = accountType;
		this.accountBalance = 0.0;
		this.transactions = new ArrayList<Transaction>();
	}
	
	public int getAccountType() {
		return accountType;
	}

	public double getAccountBalance() {
		return this.accountBalance;

	}

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			this.accountBalance += amount;
			transactions.add(new Transaction(amount));
		}
	}

	public double withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } else {
    	if (this.accountBalance > amount) {
    		this.accountBalance -= amount;
    		transactions.add(new Transaction(-amount));
    		return amount;
    	}
    	else
    		return 0.0;   		
    	}
        
    }

	public double getInterestEarned() {
		double amount = getAccountBalance();
		switch (this.accountType) {
		case SAVINGS:
			if (amount <= 1000)
				return amount * 0.001;
			else
				return 1 + (amount - 1000) * 0.002;
		case MAXI_SAVINGS:
			if (amount <= 1000)
				return amount * 0.02;
			if (amount <= 2000)
				return 20 + (amount - 1000) * 0.05;
			return 20 + (amount - 1000) * 0.05 + (amount - 2000) * 0.1;
		default:
			//Checking account
			return amount * 0.001;
		}
	}
	public double getInterestEarnedWithAnnualizedRate(int numofdays) {
		//using the formula 
		// I = Prt
		//Where Interest (I) = Principal (P) * AnnualRate (r) * Time in years(t)
		double amount = getAccountBalance();
		switch (this.accountType) {
		case SAVINGS:
			if (amount <= 1000)
				return (amount * 0.001*(numofdays/365));
			else 
				return ( 1000* 0.001*(numofdays/365) + ((amount-1000)*0.002*(numofdays/365)));
			
		case MAXI_SAVINGS:
			if (amount <= 1000)
				return (amount * 0.02*(numofdays/365));
			if (amount <= 2000)
				return ((1000 * 0.02*(numofdays/365)) + ((amount-1000)*0.05*(numofdays/365)));
			else
				return ((1000 * 0.02*(numofdays/365)) + ((amount-1000)*0.05*(numofdays/365))+ ((amount-2000)*0.1*(numofdays/365)));
		default:
			//Checking account
			return amount * 0.001*(numofdays/365);
		}
	}
	public double getInterestEarnedForMaxi(int numofdaysafterlasttransacation) {
		double interest = 0.0;
		if (numofdaysafterlasttransacation < 10){
			return (this.accountBalance *0.001);
		}
		else
			return (this.accountBalance * 0.05);
	}
	public Date getLastWithdrawalDate() {
		
		ListIterator<Transaction> it = transactions.listIterator();
		while (it.hasPrevious()) {
			Transaction t = it.previous();
			if (t.amount < 0) {
				return t.getTransactionDate();
			}	
		}
		return null;
	}
	
	public String getStatement() {
		String Statement = "";
		if (this.accountType == 0) 
			Statement = "Checking ";
		else if (this.accountType == 1) 
			Statement = "Savings ";
		else
			Statement += "Maxi-Savings ";
		Statement += "Account \n";
		
		Statement += getAllTransactions();
		
        Statement  += "Total " + this.accountBalance + "\n";
        return Statement;
	}
	
	public String getAllTransactions() {
		String Transact = "";
	       for (Transaction t : this.transactions) {
       		if (t.getAmount() < 0) 
       			Transact += " Withdraw " ;
       		else
       			Transact += " Deposit " ;
       		Transact += abs(t.getAmount()) + "\n";
       }

		return Transact;
	}
	


}
