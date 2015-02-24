package com.abc.banking.test;

import junit.framework.Assert;

import org.junit.Test;

import com.abc.banking.exception.BankingCriticalException;
import com.abc.banking.model.Customer;
import com.abc.banking.model.account.AccountFactory;
import com.abc.banking.model.account.AccountType;
import com.abc.banking.model.account.CheckingAccount;
import com.abc.banking.model.account.SavingsAccount;
import com.abc.banking.transaction.TransactionType;



public class AbstractAccountTest {

	@Test
	public void testAbstractAccount1() throws BankingCriticalException{

		Customer customer = new Customer("Vibhor Srivastava") ;

		customer.addAccount(AccountFactory.getAccount(AccountType.CHECKING));
		customer.addAccount(AccountFactory.getAccount(AccountType.SAVINGS));
		customer.addAccount(AccountFactory.getAccount(AccountType.MAXI_SAVINGS));

		customer.getAccountList().get(0).deposit(10.0) ;
		customer.getAccountList().get(0).withdraw(3.0) ;

		customer.getAccountList().get(1).deposit(11.0) ;
		customer.getAccountList().get(1).withdraw(9) ;

		customer.getAccountList().get(2).deposit(90) ;
		customer.getAccountList().get(2).withdraw(45) ;    


		Assert.assertEquals(7.0, customer.getAccountList().get(0).getAccountBalance()) ;
		Assert.assertEquals(2.0, customer.getAccountList().get(1).getAccountBalance()) ;
		Assert.assertEquals(45.0, customer.getAccountList().get(2).getAccountBalance()) ;		

	}


	@Test(expected = BankingCriticalException.class)
	public void testAbstractAccount2() throws BankingCriticalException{

		Customer customer = new Customer("Vibhor Srivastava") ;		
		customer.addAccount(AccountFactory.getAccount(AccountType.CHECKING));		
		customer.getAccountList().get(0).deposit(10.0) ;
		customer.getAccountList().get(0).withdraw(12.0) ;     

	}

	@Test(expected = BankingCriticalException.class)
	public void testAbstractAccount3() throws BankingCriticalException{

		Customer customer = new Customer("Vibhor Srivastava") ;		
		customer.addAccount(AccountFactory.getAccount(AccountType.CHECKING));		
		customer.getAccountList().get(0).deposit(-10.0) ;
		customer.getAccountList().get(0).withdraw(12.0) ;     

	}

	@Test(expected = BankingCriticalException.class)
	public void testAbstractAccount4() throws BankingCriticalException{

		Customer customer = new Customer("Vibhor Srivastava") ;		
		customer.addAccount(AccountFactory.getAccount(AccountType.CHECKING));		
		customer.getAccountList().get(0).deposit(11.0) ;
		customer.getAccountList().get(0).withdraw(-1.0) ;     

	}

	@Test
	public void testAbstractAccount5() throws BankingCriticalException{

		Customer customer = new Customer("Vibhor Srivastava") ;

		customer.addAccount(AccountFactory.getAccount(AccountType.SAVINGS));
		customer.addAccount(AccountFactory.getAccount(AccountType.CHECKING));		
		customer.getAccountList().get(0).deposit(3.0) ;
		customer.getAccountList().get(1).deposit(10.0) ;
				
		SavingsAccount savingsAccount = (SavingsAccount)customer.getAccountList().get(0);
		CheckingAccount checkingAccount = (CheckingAccount)customer.getAccountList().get(1);
		
		checkingAccount.transferFund(savingsAccount, 6);
		
		Assert.assertEquals(9.0, savingsAccount.getAccountBalance());
		Assert.assertEquals(4.0, checkingAccount.getAccountBalance());
		
		
		
	}

	@Test
	public void testAbstractAccount6() throws BankingCriticalException{

		Customer customer = new Customer("Vibhor Srivastava") ;

		customer.addAccount(AccountFactory.getAccount(AccountType.SAVINGS));	
		
		customer.getAccountList().get(0).deposit(3.0) ;
		
		customer.getAccountList().get(0).deposit(3.0) ;
		customer.getAccountList().get(0).deposit(3.0) ;		
		
		Assert.assertEquals(3 , customer.getAccountList().get(0).getTransactions().size());
		
		SavingsAccount savingsAccount = (SavingsAccount)customer.getAccountList().get(0);	
		
		Assert.assertEquals(-1 , savingsAccount.getLastTransactionAge(TransactionType.DEBIT)) ;
		
		customer.getAccountList().get(0).withdraw(1.0) ;		
		
		Assert.assertEquals(0 , savingsAccount.getLastTransactionAge(TransactionType.DEBIT)) ;	
		

	}



}
