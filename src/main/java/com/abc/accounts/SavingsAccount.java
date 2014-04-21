/**
 * 
 */
package com.abc.accounts;

/**
 * Contains specific attributes and behaviors of a savings account type.
 *
 */
public class SavingsAccount extends Account {

	public SavingsAccount() {
		super(AccountType.SAVINGS_ACCOUNT);
	}

	@Override
	public double interestEarned() {
		double amount = getBalance();
		
		if (amount <= 1000) {
            return amount * 0.001;
		}
        else {
            return 1 + (amount-1000) * 0.002;
        }
	}
	
	@Override
	public String toString() {
		return accountType.getName();
	}
}
