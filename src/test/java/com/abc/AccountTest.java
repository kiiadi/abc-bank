package com.abc;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountTest {
	
	@Test
	public void accountTest() {
		
		//Testing for Checking Account
		Account CheckingAccount = new Account(Account.CHECKING);
		assertEquals("Cannot Withdraw more than what you have", CheckingAccount.withdraw(100.00), CheckingAccount.getAccountBalance(), 1e-15);
		CheckingAccount.deposit(200.00);
		CheckingAccount.withdraw(50.00);
		//checking for account balance
		assertEquals("The Checking balance does not match", 150, CheckingAccount.getAccountBalance(), 1e-15);	
		assertEquals("The interest Calculated does not Match", (CheckingAccount.getAccountBalance()*0.001), 
				CheckingAccount.getInterestEarned(),1e-15);
		assertEquals("The sum of Transactions does not match", CheckingAccount.getAccountBalance(), 150,1e-15);
		assertEquals("Checking Account \n" +
                " Deposit 200.0\n" +
				" Withdraw 50.0\n" +
                "Total 150.0\n", CheckingAccount.getStatement() );
//**********************Testing Savings Account************************
		Account SavingAccount = new Account(Account.SAVINGS);
		SavingAccount.deposit(1000.00);
		SavingAccount.withdraw(200.00);
		assertNotEquals("Cannot withdraw more than your account balance", 800, SavingAccount.withdraw(900), 1e-15);
		assertEquals("The Savings balance does not match", 800.00, SavingAccount.getAccountBalance(),1e-15);
		assertEquals("The interest calculated does not match", SavingAccount.getAccountBalance()*0.001, 
				SavingAccount.getInterestEarned(), 1e-15);
		SavingAccount.deposit(2000.00);
		assertEquals("The Savings balance does not match", 2800.00, SavingAccount.getAccountBalance(),1e-15);
		assertEquals("The interest calculated does not match", 
				((SavingAccount.getAccountBalance()-1000.00)*0.002 + 1000.00*0.001),
				SavingAccount.getInterestEarned(), 1e-15);
		assertEquals("The sum of Transactions does not match", SavingAccount.getAccountBalance(), 2800.00,1e-15);
		assertEquals("Savings Account \n" +
                " Deposit 1000.0\n" +
				" Withdraw 200.0\n" +
                " Deposit 2000.0\n" +
                "Total 2800.0\n", SavingAccount.getStatement() );
//**********************Testing Maxi-Savings Account************************
		Account MaxiSavingAccount = new Account(Account.MAXI_SAVINGS);
		MaxiSavingAccount.deposit(3000.00);
		double accountBalance = MaxiSavingAccount.getAccountBalance();
		assertEquals("The interest calculated does not match", 
				20 + (accountBalance - 1000) * 0.05 + (accountBalance - 2000) * 0.1, 
				MaxiSavingAccount.getInterestEarned(), 1e-15);
		
		assertEquals("Maxi-Savings Account \n" +
                " Deposit 3000.0\n" +
                "Total 3000.0\n", MaxiSavingAccount.getStatement() );
		
	}
	
	@Test
	public void testSpecialInterestonMaxi() {
		Account MaxiSavingAccount = new Account(Account.MAXI_SAVINGS);
		MaxiSavingAccount.deposit(3000.00);
		double accountBalance = MaxiSavingAccount.getAccountBalance();
		//The difference between last withdrawal and today is manually calculated to be 10 here
		assertEquals("The interest calculated does not match", 
				150,MaxiSavingAccount.getInterestEarnedForMaxi(10), 1e-15);
		//The difference between last withdrawal and today is manually calculated to be 2 here
		assertEquals("The interest calculated does not match", 
				3,MaxiSavingAccount.getInterestEarnedForMaxi(2), 1e-15);
	}
	

}
