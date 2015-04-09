package com.abc.account;

public enum AccountType {
	
	CHECKING("CHECKING", "Checking"),
	SAVINGS("SAVINGS", "Savings"),
	MAX_SAVINGS("MAXSAVINGS", "MaxSavings");

	private String type;
	private String displayName;
	
	private AccountType(String type, String displayName){
		this.setType(type);
		this.setDisplayName(displayName);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
}
