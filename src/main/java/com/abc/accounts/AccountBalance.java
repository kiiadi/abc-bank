package com.abc.accounts;

import java.util.Date;

import com.abc.util.DefaultDateProvider;

public class AccountBalance {
    
	private Date date = DefaultDateProvider.getInstance().now();
    private double balance;
    private double interest;
    
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}	
	public void setBalanceAndDate(double balance, Date date) {
		this.balance = balance;
		this.date = date;
	}
}