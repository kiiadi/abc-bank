package com.abc.banking.test;

import junit.framework.Assert;

import org.junit.Test;

import com.abc.banking.model.account.Account;
import com.abc.banking.model.account.AccountFactory;
import com.abc.banking.model.account.AccountType;
import com.abc.banking.model.account.CheckingAccount;
import com.abc.banking.model.account.MaxiSavingAccount;
import com.abc.banking.model.account.SavingsAccount;



public class AccountFactoryTest {
	
	@Test
	public void testGetAccount(){		
		
		Account account1 = AccountFactory.getAccount(AccountType.CHECKING);
		Account account2 = AccountFactory.getAccount(AccountType.SAVINGS);
		Account account3 = AccountFactory.getAccount(AccountType.MAXI_SAVINGS);
		
		Assert.assertTrue(account1 instanceof CheckingAccount) ;
		Assert.assertTrue(account2 instanceof SavingsAccount) ;
		Assert.assertTrue(account3 instanceof MaxiSavingAccount) ;
		
		
		
		
		
	}

}
