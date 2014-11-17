package com.abc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

/**
 * Class representing Bank account of a customer
 * @author Rakesh
 *
 */

public class Account {

	/**
	 * Enumerated list for account types
	 * Account description is set as part of constructor initialization
	 */
	enum AccountType {
		CHECKING("Checking Account"), SAVINGS("Savings Account"), MAXI_SAVINGS(
				"Maxi Savings Account");
		AccountType(String accountDescription) {
			this.accountDescription = accountDescription;
		}

		private String accountDescription;

		public String getAccountDescription() {
			return accountDescription;
		}
	}

	/**
	 * Account type
	 */
	private final AccountType accountType;
	
	/**
	 * List of transactions
	 */
	private List<Transaction> transactions;
	
	/**
	 * Last withdrawal date on a given account
	 */
	private Date lastWithdrawalDate;

	/**
	 * Constructor
	 * @param accountType
	 */
	public Account(AccountType accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	/**
	 * Deposit amount with current date
	 * @param amount
	 */
	public void deposit(double amount) {
		deposit(amount, Calendar.getInstance().getTime());
	}

	/**
	 * Deposit amount on a given date
	 * This is not a valid real world scenario
	 * @param amount
	 * @param date
	 */
	public void deposit(double amount, Date date) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount, date));
		}
	}

	/**
	 * Withdraw amount with current date
	 * @param amount
	 */
	public void withdraw(double amount) {
		withdraw(amount, Calendar.getInstance().getTime());
	}
    
	/**
	 * Withdraw amount on a given date
	 * This is not a valid real world scenario
	 * @param amount
	 * @param date
	 */
	public void withdraw(double amount, Date date) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			double balance = sumTransactions();
			if (amount > balance) {
				throw new IllegalArgumentException(
						"amount must be less than the account balance");
			} else {
				transactions.add(new Transaction(-amount, date));
				lastWithdrawalDate = date;
			}

		}

	}

	/**
	 * Interest calculation
	 * Under the assumption that first deposit date is the starting date to calculate the number of
	 * days till today. Additional features requirements are implemented.
	 * @return void
	 */
	public double interestEarned() {
		double amount = sumTransactions();
		int numOfDays = numOfDaysForInterestCalculation();

		switch (accountType) {
		case SAVINGS:
			if (amount <= 1000)
				return ((0.001 / 365) * amount * numOfDays);
			else
				return ((1 / 365) + ((0.002 / 365) * (amount - 1000) * numOfDays));

		case CHECKING:
			return ((0.001 / 365) * amount * numOfDays);

		case MAXI_SAVINGS:
			if (isNoWithdrawalInLast10Days())
				return ((0.05 / 365) * amount * numOfDays);
			else
				return ((0.001 / 365) * amount * numOfDays);
		default:
			return (amount * 0.001 * numOfDays);
		}
	}

	/**
	 * Sum all the amounts in the transaction list - withdrawal and deposits
	 * @return net amount of all activities in the account
	 */
	public double sumTransactions() {
		double amount = 0.0;
		for (Transaction t : transactions) {
			amount += t.getAmount();
		}
		return amount;
	}

	/**
	 * Number of days for calculating interest
	 * @return Number of days from first deposit date to till today
	 */
	public int numOfDaysForInterestCalculation() {
		int numOfDays = 0;
		Date firstDepositDate = Calendar.getInstance().getTime();
		for (Transaction t : transactions) {
			if (t.getAmount() > 0) {
				firstDepositDate = t.getTransactionDate();
				break;
			}
		}
		Date currentDate = Calendar.getInstance().getTime();
		numOfDays = (int) ((currentDate.getTime() - firstDepositDate.getTime()) / (1000 * 60 * 60 * 24));
		return numOfDays;
	}

	/**
	 * Check for any withdrawal in last 10 days
	 * @return true or false 
	 */
	public boolean isNoWithdrawalInLast10Days() {
		boolean isNoWithdrawal = false;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -10);
		Date cutDate = cal.getTime();
		if (lastWithdrawalDate == null){
			isNoWithdrawal = true;
		}
		else {
			isNoWithdrawal = lastWithdrawalDate.before(cutDate);
		}		
		return isNoWithdrawal;

	}

	/**
	 * Getter method for account type
	 * @return Account type
	 */
	public AccountType getAccountType() {
		return accountType;
	}

	/**
	 * Getter method for list of transactions
	 * @return Transactions list
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}
	
	/**
	 * Getter method for last withdrawal date
	 * @return
	 */
	public Date getLastWithdrawalDate() {
		return lastWithdrawalDate;
	}
	

}
