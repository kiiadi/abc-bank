package com.abc;

public class SavingsAccount extends Account {

	private static final AccountType type = AccountType.SAVINGS ;
	
	public SavingsAccount() {
		// TODO Auto-generated constructor stub
		super(type, 0.0) ;
	}

	@Override
	public double interestEarned() {
		// TODO Auto-generated method stub
		double interestEarned = 0.0 ;
		double balance = super.getCurrBalance() ;
		if (balance <= 1000) {
			interestEarned = balance * 0.001;
		} else
        	interestEarned =  1 + ( (balance -1000) * 0.002 );
		return interestEarned;
	}

}
