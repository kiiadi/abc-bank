package com.abc.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.MaxSavingsAccount;
import com.abc.account.SavingsAccount;
import com.abc.exception.DuplicateAccountException;
import com.abc.exception.InvalidTransactionException;
import com.abc.transaction.TransactionType;
import com.abc.util.Constants;

public class CustomerTest {

    @Test
    public void testOpeningOneAccount() throws DuplicateAccountException {
        Customer oscar = new Customer("123", "John", "Doe").openAccount(new CheckingAccount("1"));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testOpeningTwoAccounts() throws DuplicateAccountException {
    	Customer oscar = new Customer("123", "John", "Doe");
    	oscar.openAccount(new CheckingAccount("1"));    	
        oscar.openAccount(new SavingsAccount("2"));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testOpeningThreeAcounts() throws DuplicateAccountException {
    	Customer oscar = new Customer("123", "John", "Doe");
    	oscar.openAccount(new CheckingAccount("1"));    	
        oscar.openAccount(new SavingsAccount("2"));
        oscar.openAccount(new MaxSavingsAccount("3"));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
    @Test(expected=DuplicateAccountException.class)
    public void testOpeningDuplicateAcount() throws DuplicateAccountException {
    	Customer oscar = new Customer("123", "John", "Doe");
    	oscar.openAccount(new CheckingAccount("1"));    	
        oscar.openAccount(new SavingsAccount("1"));
    }
    
    @Test
    public void testInterestEarnedChecking() throws DuplicateAccountException, ParseException, InvalidTransactionException {
    	Customer oscar = new Customer("123", "John", "Doe");
    	Account account = new CheckingAccount("1");
		account.setOpeningDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"));
		account.processTransaction(new BigDecimal(10000D), TransactionType.DEPOSIT);
		account.setEndDateForInterestCalc(new SimpleDateFormat("yyyy/MM/dd").parse("2015/04/09"));
		
		oscar.openAccount(account); 
		
		assertEquals(oscar.getTotalInterestEarned().setScale(2, BigDecimal.ROUND_HALF_UP), 
				 new BigDecimal(0.27D).setScale(2, BigDecimal.ROUND_HALF_UP));
    }
    
    @Test
    public void testInterestEarnedSavings() throws DuplicateAccountException, ParseException, InvalidTransactionException {
    	Customer oscar = new Customer("123", "John", "Doe");
    	Account account = new SavingsAccount("1");
		account.setOpeningDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"));
		account.processTransaction(new BigDecimal(10000D), TransactionType.DEPOSIT);
		account.setEndDateForInterestCalc(new SimpleDateFormat("yyyy/MM/dd").parse("2015/04/09"));

		oscar.openAccount(account); 
		
		assertEquals(oscar.getTotalInterestEarned().setScale(2, BigDecimal.ROUND_HALF_UP), 
				 new BigDecimal(0.50D).setScale(2, BigDecimal.ROUND_HALF_UP));
    }
 
   
    @Test
    public void testInterestEarnedMultipleAccounts() throws DuplicateAccountException, ParseException, InvalidTransactionException {
    	Customer oscar = new Customer("123", "John", "Doe");
    	Account account = new CheckingAccount("1");
		account.setOpeningDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"));
		account.processTransaction(new BigDecimal(10000D), TransactionType.DEPOSIT);
		oscar.openAccount(account); 
		account.setEndDateForInterestCalc(new SimpleDateFormat("yyyy/MM/dd").parse("2015/04/09"));

    	Account account2 = new SavingsAccount("2");
		account2.setOpeningDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"));
		account2.processTransaction(new BigDecimal(10000D), TransactionType.DEPOSIT);
		oscar.openAccount(account2); 
		account2.setEndDateForInterestCalc(new SimpleDateFormat("yyyy/MM/dd").parse("2015/04/09"));

		assertEquals(oscar.getTotalInterestEarned().setScale(2, BigDecimal.ROUND_HALF_UP),
				 new BigDecimal(0.77D).setScale(2, BigDecimal.ROUND_HALF_UP));

    }
   
    @Test
    public void testCustomerStatement() throws InvalidTransactionException, ParseException, DuplicateAccountException {

    	Customer oscar = new Customer("123", "John", "Doe");
    	Account account = new CheckingAccount("1");
		account.setOpeningDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"));
		account.processTransaction(new BigDecimal(10000D), TransactionType.DEPOSIT);
		account.getTransactions().get(0).setTransDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"));
		oscar.openAccount(account); 

    	Account account2 = new SavingsAccount("2");
		account2.setOpeningDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"));
		account2.processTransaction(new BigDecimal(10000D), TransactionType.DEPOSIT);
		account2.getTransactions().get(0).setTransDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"));
		oscar.openAccount(account2); 

		System.out.println(oscar.getCustomerStatement());
		String expectedOutout = 
				new StringBuilder().append("Statement for Customer: 	John, Doe")
				.append(Constants.LINE_SEPARATOR)
				.append("AccountNumber: 	1	AccountType: 	Checking")
				.append(Constants.LINE_SEPARATOR)
				.append("Transaction Log: ")
				.append(Constants.LINE_SEPARATOR)
				.append("2015-01-01	Deposit	$10,000.00")
				.append(Constants.LINE_SEPARATOR)
				.append("Total for Account: 	$10,000.00")
				.append(Constants.LINE_SEPARATOR)
				.append("AccountNumber: 	2	AccountType: 	Savings")
				.append(Constants.LINE_SEPARATOR)
				.append("Transaction Log: ")
				.append(Constants.LINE_SEPARATOR)
				.append("2015-01-01	Deposit	$10,000.00")
				.append(Constants.LINE_SEPARATOR)
				.append("Total for Account: 	$10,000.00")
				.append(Constants.LINE_SEPARATOR)
				.append("Total across all Accounts: 	$20,000.00")
				.toString();
				
		assertEquals(expectedOutout, oscar.getCustomerStatement());
    	
    }

}
