package com.abc;

import org.junit.Test;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;
    int customer_id =0;

    @Test
    public void firstCustomer() {
    	Bank bank = new Bank();
    	assertEquals("Error",bank.getFirstCustomer());
    	
    	Customer john = new Customer("John", customer_id++);
    	john.openAccount(new Account(Account.SAVINGS));
    	bank.addCustomer(john);
    	
    	assertEquals("John", bank.getFirstCustomer());
    }
       
    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John",customer_id++);
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill", customer_id++).openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals((100 *(.001/365)), bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account savingsAccount = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill", customer_id++).openAccount(savingsAccount));

        savingsAccount.deposit(1500.0);

        assertEquals((1 + (1500-1000) * (0.002/365)), bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account maxiAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill", customer_id++).openAccount(maxiAccount));

        maxiAccount.deposit(3000.0);

        assertEquals((3000*(.001/365)), bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    //need to test maxi saving for 10+ days.     
}
