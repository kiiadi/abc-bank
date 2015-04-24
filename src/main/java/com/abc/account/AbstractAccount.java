package com.abc.account;

import java.util.ArrayList;
import java.util.List;

import com.abc.Transaction;
import com.abc.util.BankUtils;

public abstract class AbstractAccount implements Account {

	private final AccountType accountType;
	private List<Transaction> transactions;

	public AbstractAccount(AccountType accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	/* (non-Javadoc)
	 * @see com.abc.account.Account#deposit(double)
	 */
	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			transactions.add(new Transaction(amount));
		}
	}

	/* (non-Javadoc)
	 * @see com.abc.account.Account#withdraw(double)
	 */
	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			transactions.add(new Transaction(-amount));
		}
	}

	/* (non-Javadoc)
	 * @see com.abc.account.Account#interestEarned()
	 */
	abstract public double interestEarned();

	/* (non-Javadoc)
	 * @see com.abc.account.Account#sumTransactions()
	 */
	public double sumTransactions() {
		return checkIfTransactionsExist(true);
	}

	private double checkIfTransactionsExist(boolean checkAll) {
		double amount = 0.0;
		for (Transaction t : transactions)
			amount += t.amount;
		return amount;
	}

	/* (non-Javadoc)
	 * @see com.abc.account.Account#getAccountType()
	 */
	public AccountType getAccountType() {
		return accountType;
	}

	/* (non-Javadoc)
	 * @see com.abc.account.Account#statementForAccount()
	 */
	public String statementForAccount() {
		StringBuilder s = new StringBuilder();
		s.append(this.accountType.typeName()).append("\n");

		// Now total up all the transactions
		double total = 0.0;
		for (Transaction t : this.transactions) {
			s.append("  ").append((t.amount < 0 ? "withdrawal" : "deposit"))
					.append(" ").append(BankUtils.toDollars(t.amount))
					.append("\n");
			total += t.amount;
		}
		s.append("Total ").append(BankUtils.toDollars(total));
		return s.toString();
	}
}
