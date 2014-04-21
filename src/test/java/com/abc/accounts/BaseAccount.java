package com.abc.accounts;

/***
 * Derived class to test abstract account class methods
 */
public class BaseAccount extends Account {

	public BaseAccount(AccountType accountType) {
		super(accountType);
	}
	
	@Override
	public double interestEarned() {
		return 0;
	}
}
