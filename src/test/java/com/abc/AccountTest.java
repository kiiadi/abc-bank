package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.abc.Account.ACCOUNT_TYPE;

/**
 * 
 * @author Alex Gordon
 * Test class for Account.
 *
 */
public class AccountTest {
    private Bank bank;
    private Customer john;
    private Customer bill;
    private Customer gary;
    private Customer mary;
    private final static double DELTA = 1e-15;
 
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
    	this.john = new Customer("John");
    	this.bill = new Customer("Bill");
    	this.gary = new Customer("Gary");
    	this.mary = new Customer("Mary");
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
    public void depositOK() {
    	Account checking = john.getAccounts().get(0);
    	double amount = 33.33d;
    	checking.deposit(amount);
    	assertTrue(checking.getBalance() == amount);
    }

    @Test(expected=IllegalArgumentException.class)
    public void depositException() {
    	Account checking = john.getAccounts().get(0);
    	double amount = -33.33d; //bad amount for deposit!
    	checking.deposit(amount);
    }

    @Test(expected=IllegalArgumentException.class)
    public void depositException2() {
    	Account checking = john.getAccounts().get(0);
    	double amount = 0.0d; //bad amount for deposit!
    	checking.deposit(amount);
    }

    @Test
    public void withdrawOK() {
    	Account checking = john.getAccounts().get(0);
    	double amount = 100.00d;
    	double amount2 = 30.00d;
    	checking.deposit(amount);
    	checking.withdraw(amount2);
    	assertTrue(checking.getBalance() == amount - amount2);
    }

    @Test(expected=IllegalArgumentException.class)
    public void withdrawException() {
    	Account checking = john.getAccounts().get(0);
    	double amount = checking.getBalance();
    	double amount2 = amount + 10.00d; // withdraw amount is too big
    	checking.withdraw(amount2);
    }

    @Test(expected=IllegalArgumentException.class)
    public void withdrawException2() {
    	Account checking = john.getAccounts().get(0);
    	double amount = 0.0d; //bad amount for deposit!
    	checking.withdraw(amount);
    }

    @Test
    public void interestEarnedTodayOK() {
    	Account checking = john.getAccounts().get(0);
    	double amount = 100.00d;
    	double amount2 = 30.00d;
    	double interestExpected = (amount + amount2) * Constants.CHECKING_DAILY_INTEREST_RATE;
    	checking.deposit(amount);
    	checking.deposit(amount2);
    	double interestActual = checking.interestEarnedToday();
    	assertEquals(interestExpected, interestActual, DELTA);
    }



}