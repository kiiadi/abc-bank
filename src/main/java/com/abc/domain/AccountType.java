package com.abc.domain;

public enum AccountType {
	
    CHECKING(0, "Checking"), SAVINGS(1, "Savings"), MAXI_SAVINGS(2, "Maxi Savings");
    
    private int code;
    private String type;
    
    private AccountType(int code, String type) {
    	this.code = code;
    	this.type = type;
    }
    
    public int getCode() {
    	return code;
    }
    
    public String getType() {
    	return type;
    }  
}
