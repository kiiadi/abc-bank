
package com.abc;

public enum AccountType {

	CHECKING(0, "CHECKING"), SAVINGS(1, "SAVINGS"), MAXI_SAVINGS(2, "MAXI_SAVINGS");

	private int value;
	private String description;

	private AccountType(int value, String description) {
		this.value = value;
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

}
