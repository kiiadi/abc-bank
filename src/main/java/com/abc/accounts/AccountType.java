/**
 * 
 */
package com.abc.accounts;

/**
 * Enumerates all valid account types
 *
 */
public enum AccountType {
	CHECKING_ACCOUNT("Checking Account"),
	SAVINGS_ACCOUNT("Savings Account"),
	MAXI_SAVINGS_ACCOUNT("Maxi-Savings Account");
	
	private String name;
	
	public String getName() {
		return name;
	}

	AccountType(String name) {
		this.name = name;
	}
}
