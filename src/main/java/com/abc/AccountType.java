package com.abc;

/**
 * User: mchernyak
 * Date: 2/19/14
 * Time: 5:02 PM
 */
public enum AccountType {
	CHECKING, SAVINGS, MAXI_SAVINGS;

	@Override
	public String toString() {
		switch (this) {
			case CHECKING:
				return "Checking Account";
			case SAVINGS:
				return "Savings Account";
			case MAXI_SAVINGS:
				return "Maxi Savings Account";
			default:
				throw new IllegalArgumentException();
		}
	}
}
