/**
 * 
 */
package com.abc.accounts;

/**
 * Factory for creating valid account objects based on account type.
 *
 */
public class AccountFactory {

	public static Account createAccount(AccountType accountType) {
		if(accountType == null) {
			return null;
		}
		
		Account account = null;

		switch(accountType) {
			case CHECKING_ACCOUNT:
				account = new CheckingAccount();
				break;
			case SAVINGS_ACCOUNT:
				account = new SavingsAccount();
				break;
			case MAXI_SAVINGS_ACCOUNT:
				account = new MaxiSavingsAccount();
				break;
		}
		
		return account;
	}
}
