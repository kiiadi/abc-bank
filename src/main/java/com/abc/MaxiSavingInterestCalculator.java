package com.abc;

import java.util.ListIterator;

import com.abc.BankConstants.TransactionType;

public class MaxiSavingInterestCalculator implements InterestCalculator {

	public double calculate(Account account) {
		boolean eligibleForHigherRate = false;
		ListIterator<Transaction> itr = account.getTransactions().listIterator(account.getTransactions().size());
		while (itr.hasPrevious()) {
			Transaction t = itr.previous();
			// last withdrawal transaction is sufficient to make decision
			if (t.getTransactionType() == TransactionType.WITHDRAWAL) {
				eligibleForHigherRate = DateProvider
						.getDiffInDays(t.getTransactionDate()) >= 10;
				break;
			}/* if you hit any transaction where difference from today is 10 or more is enough to make decision */ 
			else if (DateProvider.getDiffInDays(
					t.getTransactionDate()) >= 10) {
				eligibleForHigherRate = true;
				break;
			}
		}
		if (eligibleForHigherRate) {
			return account.getAmount() * 0.05 / BankConstants.DAYS_IN_A_YEAR;
		} else {
			return account.getAmount() * 0.001 / BankConstants.DAYS_IN_A_YEAR;
		}
	}
}
