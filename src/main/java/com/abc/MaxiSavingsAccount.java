package com.abc;

public class MaxiSavingsAccount extends Account {

	private static final AccountType type = AccountType.SAVINGS ;
	
	public MaxiSavingsAccount() {
		// TODO Auto-generated constructor stub
		super(type, 0.0) ;
	}

	@Override
	public double interestEarned() {
		// TODO Auto-generated method stub
		double interestEarned = 0.0 ;
		double balance = super.getCurrBalance() ;
		if (balance <= 1000) {
			interestEarned = balance * 0.02;
		} else if (balance <= 2000) {
			interestEarned = 20 + ( balance - 1000 ) * 0.05;
		} else  {
        	interestEarned =  70 + ( (balance - 2000) * 0.1 );
		}
		return interestEarned;
	}

}
