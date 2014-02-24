package com.abc;

public enum AccountType {
	CHECKING(0, "CHECKING"), SAVINGS(1, "SAVINGS"), MaxiSavings(2,
			"MAXI_SAVINGS");

	private int typeValue;
	private String description;

	private AccountType(int value, String title) {
		this.typeValue = value;
		this.description = title;
	}

	public int getTypeValue() {
		return typeValue;
	}

	public String getDescription() {
		return description;
	}

}
