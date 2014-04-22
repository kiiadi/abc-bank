package com.abc.account;

import static com.abc.account.TransactionType.Deposit;
import static com.abc.account.TransactionType.Withdrawal;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Account is immutable as list if transactions is cloned and returned. Any add/remove on the 
 * returned list doesn't affect the original transactions on account.
 * No need to do a deep clone the List<Transaction>, as the Transaction itself is immutable.
 * TODO: Add account identifier
 * @author ashok
 *
 */
public abstract class Account {

	private final List<Transaction> transactions = new ArrayList<Transaction>();

	/**
	 * Making transactions immutable
	 * @return ArrayList<Transaction> copy of transactions
	 * Don't need to deep clone as Transaction itself is immutable.
	 */
	public List<Transaction> getTransactions() {
		return new ArrayList<Transaction>(transactions);
	}
	
	/**
	 * Type of the account
	 * @return AccountType
	 */
	public abstract AccountType getType();
	
	/**
	 * Minimal balance to maintain the account
	 * @return BigDecimal
	 */
	public abstract BigDecimal getMinBalance();

	public final void deposit(BigDecimal amount) {
		validateAmount(amount);
		transactions.add(new Transaction(amount, Deposit));
	}

	public final void withdraw(BigDecimal amount) {
		validateAmount(amount);
		hasEnoughBalanceToWithdraw(this, amount);
		transactions.add(new Transaction(amount, Withdrawal));
	}

	private static void hasEnoughBalanceToWithdraw(Account account, BigDecimal amount) {
		BigDecimal currentBalance = account.sumTransactions();
		if(currentBalance.subtract(amount).compareTo(account.getMinBalance()) < 0) {
			throw new RuntimeException("Account does not has enough balance or violates minimum balance restriction");
		}
	}
	private static void validateAmount(BigDecimal amount) {
		if (amount == null) {
			throw new NullPointerException("amount cannot be null");
		}
		if (amount.compareTo(ZERO) != 1) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		}
		if(amount.scale() > 2) {
			throw new IllegalArgumentException("amount scale cannot be greater than 2. Bank does not support fractional cents for amounts used for deposit or withdrawal");
		}
	}

	public abstract BigDecimal interestEarned();

	public final BigDecimal sumTransactions() {
		BigDecimal sum = ZERO;
		for (Transaction t : transactions) {
			switch (t.getType()) {
			case Withdrawal:
				sum = sum.subtract(t.getAmount());
				break;
			case Deposit:
				sum = sum.add(t.getAmount());
				break;
			}
		}
		return sum;
	}

}
