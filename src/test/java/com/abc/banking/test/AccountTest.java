package com.abc.banking.test;

import junit.framework.Assert;

import org.junit.Test;

import com.abc.banking.exception.BankingCriticalException;
import com.abc.banking.model.account.AccountFactory;
import com.abc.banking.model.account.AccountType;
import com.abc.banking.model.account.CheckingAccount;
import com.abc.banking.model.account.MaxiSavingAccount;
import com.abc.banking.model.account.SavingsAccount;

public class AccountTest {
	
	private static final double DOUBLE_DELTA = 1e-15;
	
	@Test
	public void testSavingsAccount() throws BankingCriticalException{
		
		SavingsAccount account = (SavingsAccount)AccountFactory.getAccount(AccountType.SAVINGS);
		
		account.deposit(1500);
		Assert.assertEquals(2.0, account.calculateInterest() , DOUBLE_DELTA ) ;
		
		account.withdraw(800);
		Assert.assertEquals(0.7, account.calculateInterest() , DOUBLE_DELTA  ) ;		
		
	}
	
	@Test
	public void testCheckingAccount() throws BankingCriticalException{
		
		CheckingAccount account = (CheckingAccount)AccountFactory.getAccount(AccountType.CHECKING);
		
		account.deposit(1500);
		Assert.assertEquals(1.5, account.calculateInterest() , DOUBLE_DELTA ) ;
		
		account.withdraw(800);
		Assert.assertEquals(0.7, account.calculateInterest() , DOUBLE_DELTA  ) ;		
		
	}
	
	@Test
	public void testMaxiSavingAccount() throws BankingCriticalException{
		
		MaxiSavingAccount account = (MaxiSavingAccount)AccountFactory.getAccount(AccountType.MAXI_SAVINGS);
		
		account.deposit(1000);
		Assert.assertEquals(50.0, account.calculateInterest() , DOUBLE_DELTA ) ;
		
		account.withdraw(800);
		Assert.assertEquals(0.2, account.calculateInterest() , DOUBLE_DELTA  ) ;		
		
	}

}
