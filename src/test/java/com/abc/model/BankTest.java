package com.abc.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.abc.account.CheckingAccount;
import com.abc.exception.DuplicateAccountException;
import com.abc.exception.DuplicateCustomerException;
import com.abc.util.Constants;

public class BankTest {

    @Test
    public void testAddCustomer() throws DuplicateAccountException, DuplicateCustomerException {
        Bank bank = new Bank();
        Customer john = new Customer("123", "John", "Doe");
        john.openAccount(new CheckingAccount("1"));
        bank.addCustomer(john);

        assertNotNull(bank.getCustomer("123"));
    }

    @Test(expected=DuplicateCustomerException.class)
    public void testAddDuplicateCustomer() throws DuplicateCustomerException {
        Bank bank = new Bank();
        Customer john = new Customer("123", "John", "Doe");
        bank.addCustomer(john);

        Customer john2 = new Customer("123", "John2", "Doe2");
        bank.addCustomer(john2);
    }
    
    @Test
    public void testOpenAccount() throws DuplicateAccountException, DuplicateCustomerException {
        Bank bank = new Bank();
        Customer john = new Customer("123", "John", "Doe");
        john.openAccount(new CheckingAccount("1"));
        bank.addCustomer(john);
        
        assertEquals(bank.getCustomer("123").getAccountMap().size(), 1);
    }
    
    @Test
    public void testGetCustomerSummary() throws DuplicateAccountException, DuplicateCustomerException {
        Bank bank = new Bank();
        Customer john = new Customer("123", "John", "Doe");
        john.openAccount(new CheckingAccount("1"));
        bank.addCustomer(john);

        String expectedOutout = 
				new StringBuilder().append("Customer Summary ")
				.append(Constants.LINE_SEPARATOR)
				.append("John, Doe	Number of Accounts: 1")
				.toString();
        
        assertEquals(expectedOutout, bank.getCustomerSummary());
    }
    
    @Test
    public void testGetTotalInterestPaidStatement() throws DuplicateAccountException, DuplicateCustomerException {
        Bank bank = new Bank();
        Customer john = new Customer("123", "John", "Doe");
        john.openAccount(new CheckingAccount("1"));
        bank.addCustomer(john);

        String expectedOutout = "Total Interest paid by Bank on all Accounts: 	$0.00";
        
        assertEquals(expectedOutout, bank.getTotalInterestPaidStatement());
    }
 
}
