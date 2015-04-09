package com.abc.transaction;

public enum TransactionType {
	
	DEPOSIT("DEPOSIT", "Deposit"),
	WITHDRAW("WITHDRAW", "Withdraw"),
	TRANSFER("TRANSFER", "Transfer");

	private String type;
	private String displayName;
	
	private TransactionType(String type, String displayName){
		this.setType(type);
		this.setDisplayName(displayName);
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
