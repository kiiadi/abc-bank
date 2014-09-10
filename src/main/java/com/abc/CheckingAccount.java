package com.abc;

public class CheckingAccount extends Account {

	private final static AccountType type = AccountType.CHECKING ;
	
	public CheckingAccount() {
		// TODO Auto-generated constructor stub
		super(type, 0.0) ;
	}

	@Override
	public double interestEarned() {
		// TODO Auto-generated method stub
		return super.interestEarned();
	}

}
