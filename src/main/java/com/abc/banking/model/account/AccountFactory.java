package com.abc.banking.model.account;

/**
 * Factory to generate accounts
 *
 */
public class AccountFactory {

	/**
	 * Returns the concrete implementation of account interface
	 * @param accountType
	 * @return account
	 */
	public static Account getAccount(AccountType accountType) {

		if (accountType.equals(AccountType.SAVINGS)) {
			return new SavingsAccount();
		} else if (accountType.equals(AccountType.CHECKING)) {
			return new CheckingAccount();
		} else if (accountType.equals(AccountType.MAXI_SAVINGS)) {
			return new MaxiSavingAccount();
		}


		return null;

	}



}
