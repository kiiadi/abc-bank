package com.abc.account;

public class AccountFactory {
	public Account create(AccountType accountType) {
		Account ac = null;
		switch (accountType) {
		case CHECKING:
			ac = new CheckingAccount(accountType);
			break;
		case SAVINGS:
			ac = new SavingsAccount(accountType);
			break;
		case MAXI_SAVINGS:
			ac = new MaxiSavingsAccount(accountType);
			break;
		default:
			throw new UnsupportedOperationException(
					"Don't know how to create account of type: " + accountType);
		}
		return ac;
	}
}
