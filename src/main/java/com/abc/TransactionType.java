package com.abc;

public enum TransactionType {

	DEPOSIT(0,"deposit"), WITHDRAWAL(1,"withdrawal");
	
	  private final int code;
	  private final String description;

	  private TransactionType(int code, String description) {
	    this.code = code;
	    this.description = description;
	  }

	  public String getDescription() {
	     return description;
	  }

	  public int getCode() {
	     return code;
	  }

	  @Override
	  public String toString() {
	    return code + ": " + description;
	  }
	

}
