package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {

	private List<Transaction> transactions;

	private BigDecimal balance;

	public Account(BigDecimal startingBalance) {
		this.balance = startingBalance;
		this.transactions = new ArrayList<Transaction>();
		transactions.add(new Transaction(startingBalance));
	}
	
	//Used for testing purposes
	public Account(BigDecimal startingBalance, int dayInterval) {
		this.balance = startingBalance;
		this.transactions = new ArrayList<Transaction>();
		transactions.add(new Transaction(startingBalance, dayInterval));
	}

	public synchronized void deposit(BigDecimal amount) {
		Utils.checkIfAmountLessOrEqualToZero(amount);
		transactions.add(new Transaction(amount));
		balance = balance.add(amount);
	}

	//Used for testing purposes
	public synchronized void deposit(BigDecimal amount, int dayInterval) {
		Utils.checkIfAmountLessOrEqualToZero(amount);
		transactions.add(new Transaction(amount, dayInterval));
		balance = balance.add(amount);
	}
	
	public synchronized void withdraw(BigDecimal amount) {
		Utils.checkIfAmountLessOrEqualToZero(amount);
		Utils.checkIfBalanceIsLessThanAmount(balance, amount);
		transactions.add(new Transaction(amount.negate()));
		balance = balance.subtract(amount);
	}

	//Used for testing purposes
	public synchronized void withdraw(BigDecimal amount, int dayInterval) {
		Utils.checkIfAmountLessOrEqualToZero(amount);
		Utils.checkIfBalanceIsLessThanAmount(balance, amount);
		transactions.add(new Transaction(amount.negate(), dayInterval));
		balance = balance.subtract(amount);
	}
	
	public synchronized void transfer(Account toAccount, BigDecimal amount) {
		Utils.checkIfAmountLessOrEqualToZero(amount);
		if(this.sumTransactions().subtract(amount).signum() == -1){
			throw new RuntimeException(
					"Transfer failed due to insufficient funds");
		}
		transactions.add(new Transaction(amount.negate()));
		balance = balance.subtract(amount);
		toAccount.getTransactions().add(new Transaction(amount));
		toAccount.setBalance(toAccount.getBalance().add(amount));
	}
	
	public synchronized BigDecimal sumTransactions() {
		//Utils.checkIfTransactionsExist(getTransactions());
		//Not Needed anymore as the transaction list will never be empty.
		BigDecimal amount = BigDecimal.ZERO;
		for (Transaction t : transactions)
			amount = amount.add(t.getAmount());
		return amount;
	}
   
	//To be implemented by concrete classes
    public abstract BigDecimal interestEarned();

	public synchronized List<Transaction> getTransactions() {
		return transactions;
	}

	public synchronized BigDecimal getBalance() {
		return balance;
	}

	public synchronized void setBalance(BigDecimal balance) {
		this.balance = balance;
	}


    

}
