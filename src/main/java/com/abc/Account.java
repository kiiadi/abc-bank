package com.abc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Alex Gordon - Account class
 *
 */
public class Account {
	
	private String customerName;

	// enum: - account types
	public static enum ACCOUNT_TYPE {
		CHECKING("Checking Account"), SAVINGS("Saving Account"), MAXI_SAVINGS("Maxi-Saving Account");

		private final String accountTypeDescription;

		private ACCOUNT_TYPE(String accountTypeDescription) {
			this.accountTypeDescription = accountTypeDescription;
		}

		public String getAccountTypeDescription() {
			return accountTypeDescription;
		}
	}

	private final ACCOUNT_TYPE accountType;
	private String accountTypeDescription;
	private List<Transaction> transactions;
	private double balance;
	private double interest;

	// Constructor
	public Account(String customerName, ACCOUNT_TYPE accountType) {
		this.customerName = customerName;
		this.accountType = accountType;
		this.accountTypeDescription = accountType.accountTypeDescription;
		this.transactions = new ArrayList<>();
		this.balance = 0.0d;
	}

	/**
	 * deposit()
	 * 
	 * @param amount
	 *            - must not be negative
	 */
	public void deposit(double amount) {
		if (amount <= 0.0d) {
			throw new IllegalArgumentException(Constants.NEGATIVE_AMOUNT_ERROR);
		} else {
			this.transactions.add(new Transaction(Instant.now(), amount));
			this.balance += amount;
		}
	}

	/**
	 * withdraw()
	 * 
	 * @param amount
	 *            - must not be negative. When transaction is written into the
	 *            system them amount is made negative for withdraw
	 */
	public void withdraw(double amount) {
		if (amount <= 0.0) {
			throw new IllegalArgumentException(Constants.NEGATIVE_AMOUNT_ERROR);
		} else if (amount > this.balance) {
			throw new IllegalArgumentException(Constants.NOT_ENOUGH_BALANCE_ERROR);
		} else {
			transactions.add(new Transaction(Instant.now(), -amount));
			this.balance -= amount;
		}
	}

	public double interestEarnedToday() {
		double interest;
		switch (this.accountType) {
		case CHECKING:
			interest = this.balance * Constants.CHECKING_DAILY_INTEREST_RATE;
			return interest;
		case SAVINGS:
			interest = this.balance * Constants.SAVING_DAILY_INTEREST_RATE_1;
			if (this.balance <= 1000.0) {
				return interest;
			} else {
				interest = 1000.0 * Constants.CHECKING_DAILY_INTEREST_RATE;
				return interest + (this.balance - 1000.0) * Constants.SAVING_DAILY_INTEREST_RATE_2;
			}
		case MAXI_SAVINGS:
			interest = this.balance * Constants.MAXI_SAVING_DAILY_INTEREST_RATE_1;
			if (this.balance <= 1000.0) {
				return interest;
			} else if (this.balance <= 2000.0) {
				interest = 1000.0 * Constants.MAXI_SAVING_DAILY_INTEREST_RATE_1;
				return interest + (this.balance - 1000.0) * Constants.MAXI_SAVING_DAILY_INTEREST_RATE_2;
			} else {
				interest = 1000.0 * Constants.MAXI_SAVING_DAILY_INTEREST_RATE_1;
				interest += 1000.0 * Constants.MAXI_SAVING_DAILY_INTEREST_RATE_2;
				return interest + (this.balance - 1000.0) * Constants.MAXI_SAVING_DAILY_INTEREST_RATE_3;
			}
		default:
			//never:
			return -1;
		}
	}

	public ACCOUNT_TYPE getAccountType() {
		return accountType;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public String getAccountTypeDescription() {
		return accountTypeDescription;
	}

	public double getBalance() {
		return balance;
	}

	public String statement() {
		String s = "ACCOUNT: ";
		s += this.getAccountTypeDescription() + "\n";

		// total withdrawals=? 
		// total deposits=?
		double totalDeposit = 0.0;
		double totalWithdraw = 0.0;
		for (Transaction t : this.transactions) {
			s += "  " + t + "\n";
			switch (t.getTransactionType()) {
			case DEPOSIT:
				totalDeposit += t.getAmount();
				break;
			case WITHDRAW:
				totalWithdraw += t.getAmount();
				break;
			}
		}
		s += "Total deposits for account: " + Constants.toDollars(totalDeposit) + "\n";
		s += "Total withdraws for account: " + Constants.toDollars(totalWithdraw) + "\n";
		s += "Balance for account: " + Constants.toDollars(this.balance) + "\n";
		return s;
	}

}
