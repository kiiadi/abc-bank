package com.abc.accounts;

public enum AccountType {
	
	CHECKING ("Checking Account"),
	SAVINGS ("Savings Account"),
	MAX_SAVINGS("Maxi Savings Account");
	
	private String accountDescription;
	
	AccountType(String accountDescription) {
		this.accountDescription = accountDescription;
	}

	public String getDescription() {
		return accountDescription;
	}
}