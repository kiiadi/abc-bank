package com.abc;

public class AccountFactory {
	
	public static Account createAccount(AccountType type) {
		Account account = null;
		switch (type) {
		case CHECKING:
			account = new CheckingAccount();
			break;
		case SAVINGS:
			account = new SavingsAccount();
			break;
		case MAXI_SAVINGS:
			account = new MaxiSavingsAccount();
			break;
		default:
			throw new IllegalArgumentException("Not an allowed account");
		}
		return account;
	}
}
