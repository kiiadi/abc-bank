package com.abc;

public enum AccountType {

	CHECKING(0,"Checking Account"), SAVINGS(1,"Savings Account"), MAXI_SAVINGS(2,"Maxi Savings Account");
	
	  private final int code;
	  private final String description;

	  private AccountType(int code, String description) {
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
