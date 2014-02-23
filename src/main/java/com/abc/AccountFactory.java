package com.abc;

/**
 * User: mchernyak
 * Date: 2/22/14
 * Time: 12:23 PM
 */
public class AccountFactory {

	public static Account createAccount(AccountType accountType) throws IllegalArgumentException {

		switch (accountType) {
			case CHECKING:
				return new CheckingAccount();

			case SAVINGS:
				return new SavingsAccount();

			case MAXI_SAVINGS:
				return new MaxiSavingsAccount();

			default:
				throw new IllegalArgumentException();
		}
	}
}
