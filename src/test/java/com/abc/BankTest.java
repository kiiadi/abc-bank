package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.abc.Account.ACCOUNT_TYPE;

/**
 * 
 * @author Alex Gordon
 * Test class for Bank.
 *
 */
public class BankTest {
    private Bank bank;
 
    @Before
    public void init() {
    	// One bank
    	// Four customers: John, Bill, Gary and Mary
    	// Accounts:
    	// John - checking, saving and maxi-saving.
    	// Bill - checking and saving
    	// Gary - saving and maxi-saving.
    	// Mary - maxi-saving.
    	this.bank = new Bank();
    	Customer john = new Customer("John");
    	Customer bill = new Customer("Bill");
    	Customer gary = new Customer("Gary");
    	Customer mary = new Customer("Mary");
    	bank.addCustomer(john);
    	bank.addCustomer(bill);
    	bank.addCustomer(gary);
    	bank.addCustomer(mary);
    	john.openAccount(new Account(john.getName(), ACCOUNT_TYPE.CHECKING));
    	john.openAccount(new Account(john.getName(),ACCOUNT_TYPE.SAVINGS));
    	john.openAccount(new Account(john.getName(),ACCOUNT_TYPE.MAXI_SAVINGS));
    	bill.openAccount(new Account(bill.getName(),ACCOUNT_TYPE.CHECKING));
    	bill.openAccount(new Account(bill.getName(),ACCOUNT_TYPE.SAVINGS));
    	gary.openAccount(new Account(gary.getName(),ACCOUNT_TYPE.SAVINGS));
    	gary.openAccount(new Account(gary.getName(),ACCOUNT_TYPE.MAXI_SAVINGS));
    	mary.openAccount(new Account(mary.getName(),ACCOUNT_TYPE.MAXI_SAVINGS));
    	
    	// TODO more data as needed
    	
    }

    @Test
    public void testCustomerSummaryReportOK() {
    	String expected;
    	String actual;
    	expected = "Customer Summary Report\n" +
    	 " - Customer Name: John, Number Of Accounts: 3\n" +
    	 " - Customer Name: Bill, Number Of Accounts: 2\n" +
    	 " - Customer Name: Gary, Number Of Accounts: 2\n" +
    	 " - Customer Name: Mary, Number Of Accounts: 1";
    	actual = bank.customerSummaryReport();
        assertEquals(expected, actual);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAddCustomerException() {
    	Customer robert1 = new Customer("Robert");
    	bank.addCustomer(robert1);
    	Customer robert2 = new Customer("Robert");
    	bank.addCustomer(robert2);
    }

    @Test
    public void testtotalInterestPaidTodayReportOK() {
    	String expected;
    	String actual;
    	expected = "Total Interest Paid Today\n" +
         " - $0.00";
    	actual = bank.totalInterestPaidTodayReport();
        assertEquals(expected, actual);
    }

}