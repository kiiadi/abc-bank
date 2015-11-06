package com.abc;

public class AccountTypes {
	public static final int CHECKING = 0;
	public static final int SAVINGS = 1;
	public static final int MAXI_SAVINGS = 2;

	public static String getAccountType(int accountType) {
		switch (accountType) {
			case CHECKING:
				return "Checking Account";
			case SAVINGS:
				return "Savings Account";
			case MAXI_SAVINGS:
				return "Maxi Savings Account";
			default:
				throw new IllegalArgumentException("Unknow Account Type "+accountType);
		}
	}
}
