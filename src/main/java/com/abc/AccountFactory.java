
package com.abc;

public class AccountFactory {
	public static Account create(int accountType) {
		Account ac = null;
		switch (accountType) {
		case AccountTypes.CHECKING:
			ac = new CheckingAccount(accountType);
			break;
		case AccountTypes.SAVINGS:
			ac = new SavingsAccount(accountType);
			break;
		case AccountTypes.MAXI_SAVINGS:
			ac = new MaxiSavingsAccount(accountType);
			break;
		default:
			throw new IllegalArgumentException(
					"Don't know how to create account of type: "  +accountType);
		}
		return ac;
	}
}
