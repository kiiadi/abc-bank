/**
 * 
 */
package com.abc.accounts;

/**
 * Contains specific attributes and behaviors of a checking account type.
 *
 */
public class CheckingAccount extends Account {

	public CheckingAccount() {
		super(AccountType.CHECKING_ACCOUNT);
	}

	@Override
	public double interestEarned() {
		return getBalance() * 0.001;
	}
	
	@Override
	public String toString() {
		return accountType.getName();
	}
}
