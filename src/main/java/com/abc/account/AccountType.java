package com.abc.account;

public enum AccountType {
	
	CHECKING("Checking Account"), 
	SAVINGS("Savings Account"), 
	MAXI_SAVINGS("Maxi Savings Account");

	private String accountTypeName;

	private AccountType(String name) {
		this.accountTypeName = name;
	}

	public String typeName() {
		return accountTypeName;
	}
}
