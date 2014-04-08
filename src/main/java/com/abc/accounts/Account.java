package com.abc.accounts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.abc.Transaction;
import com.abc.util.DefaultCalculator;
import com.abc.util.DefaultDateProvider;

public class Account {
    
    private final AccountType accountType;
    private final AccountBalance accountBalance = new AccountBalance();
    private final List<Transaction> transactions = new ArrayList<>();
    private final DefaultCalculator calculator = new DefaultCalculator();
    
    public Account(AccountType accountType) {
    	this.accountType = accountType;    
    }

    public void deposit(double amount, Date date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
            accountBalance.setBalanceAndDate(calculateAccountBalance(date).getBalance() + amount, date);
        }
    }

    public void withdraw(double amount, Date date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            
        	double balance = getCurrentAccountBalance();
            if (amount > balance) {
                throw new IllegalArgumentException("insufficient funds");
            } else {
                transactions.add(new Transaction(-amount));
                accountBalance.setBalanceAndDate(balance - amount, date);
            }
        }
    }    
    
    public void transferFunds(Account destination, double amount) {
        withdraw(amount, DefaultDateProvider.getInstance().now());
        destination.deposit(amount, DefaultDateProvider.getInstance().now());
    }

    public double getCurrentAccruedInterest () {
        return calculateAccountBalance(DefaultDateProvider.getInstance().now()).getInterest();
    }
    
    public double getCurrentAccountBalance() {
        return calculateAccountBalance(DefaultDateProvider.getInstance().now()).getBalance();
    }

    private AccountBalance calculateAccountBalance(Date date) {
		return calculator.calculate(accountType, accountBalance, date);
	}

	public String getAccountDescription() {
        return accountType.getDescription();
	}
    public AccountBalance getAccountBalance() {
    	return accountBalance;
    }    
    public AccountType getAccountType() {
        return accountType;
    }    
    public List<Transaction> getTransactions() {
    	return transactions;
    }    
}
