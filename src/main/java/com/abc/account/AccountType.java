package com.abc.account;

/**
 * Enumeration of Account Type(s)
 * @author ashok
 *
 */
public enum AccountType {

	CHECKING("Checking Account"),
	SAVINGS("Savings Account"),
	MAXI_SAVINGS("Maxi Savings Account");

	private AccountType(String description) {
		this.description = description;
	}

	private String description;

	public String getDescription() {
		return description;
	}
}
