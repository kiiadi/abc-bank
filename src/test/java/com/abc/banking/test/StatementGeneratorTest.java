package com.abc.banking.test;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import com.abc.banking.exception.BankingCriticalException;
import com.abc.banking.model.Bank;
import com.abc.banking.model.Customer;
import com.abc.banking.model.account.AccountFactory;
import com.abc.banking.model.account.AccountType;
import com.abc.banking.statement.StatementGenerator;

public class StatementGeneratorTest {
	
	@Test
	public void testStatement1() throws BankingCriticalException{
		
		Bank bank = new Bank();
		Customer customer = new Customer("Vibhor Srivastava") ;
		StatementGenerator stateMentGen = new StatementGenerator();		
		customer.addAccount(AccountFactory.getAccount(AccountType.CHECKING));
		
		customer.getAccountList().get(0).deposit(10.0) ;
		customer.getAccountList().get(0).withdraw(3.0) ;
		
		ArrayList<Customer> list = new ArrayList<Customer>();
		list.add(customer);
		
		bank.setCustomers(list);	
		
		String result1  ="BANK DETAILS\n"+"==============\n"+"Name : Vibhor Srivastava     NUMBER OF ACCOUNTS: 1\n" ;
		
		Assert.assertEquals(result1, stateMentGen.generateStatement(bank)) ;
		
		String result2 = "CUSTOMER STATEMENT\n"
				+ "==============\n"
				+ "Name : Vibhor Srivastava\n"
				+ "ACCOUNT TYPE : CHECKING     7.0\n"
				+ "\n"
				+ "TRANSACTIONS\n"
				+ "==============\n"
				+ customer.getAccountList().get(0).getTransactions().get(0)
						.getTransactionDate()
				+ "     CREDIT     MONEY DEPOSITED     10.0\n"
				+ "\n"
				+ customer.getAccountList().get(0).getTransactions().get(1).getTransactionDate()
				+"     DEBIT     MONEY WITHDRAWN     3.0\n\n";

			
		Assert.assertEquals(result2, stateMentGen.generateStatement(customer)) ;	
		
		
		String result3  ="TOTAL INTEREST PAID : 0.0070";		
		Assert.assertEquals(result3, stateMentGen.generateBankInterestPaidStatement(bank)) ;	
		
		
	}

	

}
