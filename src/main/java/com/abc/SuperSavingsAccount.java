package com.abc;

public class SuperSavingsAccount extends Account {

	private static final AccountType type = AccountType.SUPER_SAVINGS ;
	
	public SuperSavingsAccount() {
		// TODO Auto-generated constructor stub
		super(type, 0.0) ;
	}

	@Override
	public double interestEarned() {
		// TODO Auto-generated method stub
		double interestEarned = 0.0 ;
		double balance = super.getCurrBalance() ;
		if (balance <= 4000) {
			interestEarned = 70 ;
		} else
        	interestEarned =  super.interestEarned();
		return interestEarned;
	}

}
