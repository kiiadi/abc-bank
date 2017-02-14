package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import com.abc.Account.ACCOUNT_TYPE;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

public class CustomerTest {
    private Bank bank;
    private Customer john;
    private Customer bill;
    private Customer gary;
    private Customer mary;

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
    public void testcustomerSummaryReportOK() {
    	String expected;
    	String actual;
    	expected = "Statement for John\n" +
    			"ACCOUNT: Checking Account\n" +
    			"Total deposits for account: $0.00\n" +
    			"Total withdraws for account: $0.00\n" +
    			"Balance for account: $0.00\n\n" +
    			"ACCOUNT: Saving Account\n" +
    			"Total deposits for account: $0.00\n" +
    			"Total withdraws for account: $0.00\n" +
    			"Balance for account: $0.00\n\n" +
    			"ACCOUNT: Maxi-Saving Account\n" +
    			"Total deposits for account: $0.00\n" +
    			"Total withdraws for account: $0.00\n" +
    			"Balance for account: $0.00\n\n" +
    			"Total Balance In All Accounts: $0.00";
    	actual = john.getStatement();
        assertEquals(expected, actual);
    }

    @Test
    public void openAccountOK() {
    	assertTrue(john.getAccounts().size() == 3);
    	assertTrue(bill.getAccounts().size() == 2);
    	assertTrue(gary.getAccounts().size() == 2);
    	assertTrue(mary.getAccounts().size() == 1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void openAccountException() {
    	Account account = john.getAccounts().get(0);
    	john.openAccount(account);
    }

    @Test
    public void getNumberOfAccountsOK() {
    	assertTrue(john.getAccounts().size() == john.getNumberOfAccounts());
    	assertTrue(bill.getAccounts().size() == bill.getNumberOfAccounts());
    	assertTrue(gary.getAccounts().size() == gary.getNumberOfAccounts());
    	assertTrue(mary.getAccounts().size() == mary.getNumberOfAccounts());
    }

    @Test
    public void transferOK() {
    	Account fromAccount = john.getAccounts().get(0);
    	Account toAccount = john.getAccounts().get(1);
    	fromAccount.deposit(100.0d);
    	toAccount.deposit(200.0d);
    	john.transfer(fromAccount, toAccount, 20.0d);
    	assertTrue(fromAccount.getBalance() == 80.00d);
    	assertTrue(toAccount.getBalance() == 220.00d);
    }

    @Test(expected=IllegalArgumentException.class)
    public void transferException1() {
    	Account fromAccount = john.getAccounts().get(0);
    	Account toAccount = bill.getAccounts().get(1);
    	fromAccount.deposit(100.0d);
    	toAccount.deposit(200.0d);
    	john.transfer(fromAccount, toAccount, 20.0d); //toAcount is NOT your account!!!
    }

    @Test(expected=IllegalArgumentException.class)
    public void transferException2() {
    	Account fromAccount = bill.getAccounts().get(0);
    	Account toAccount = john.getAccounts().get(1);
    	fromAccount.deposit(100.0d);
    	toAccount.deposit(200.0d);
    	john.transfer(fromAccount, toAccount, 20.0d); //fromAcount is NOT your account!!!
    }

    @Test(expected=IllegalArgumentException.class)
    public void transferException3() {
    	Account fromAccount = bill.getAccounts().get(0);
    	Account toAccount = bill.getAccounts().get(1);
    	fromAccount.deposit(100.0d);
    	toAccount.deposit(200.0d);
    	john.transfer(fromAccount, toAccount, 120.0d); //transfer amount is too big!
    }

    @Test(expected=IllegalArgumentException.class)
    public void transferException4() {
    	Account fromAccount = bill.getAccounts().get(0);
    	Account toAccount = bill.getAccounts().get(1);
    	fromAccount.deposit(100.0d);
    	toAccount.deposit(200.0d);
    	john.transfer(fromAccount, toAccount, -20.00d); //negative transfer amount!
    }

    
}
