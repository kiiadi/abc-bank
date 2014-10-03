package com.abc;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-12;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new CheckingAccount());
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void customerSummaryTwoCustomers() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        Customer mary = new Customer("Mary");
        john.openAccount(new CheckingAccount());
        bank.addCustomer(john);	
        bank.addCustomer(mary);

        assertEquals("Customer Summary\n - John (1 account)\n - Mary (0 account)", bank.customerSummary());
    }
    
    @Test
    public void customerSummaryTwoAccounts() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new CheckingAccount());
        john.openAccount(new SavingsAccount());
        bank.addCustomer(john);	

        assertEquals("Customer Summary\n - John (2 accounts)", bank.customerSummary());
    }
    
    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new CheckingAccount();
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savingsAccount() {
        Bank bank = new Bank();
        Account savingsAccount = new SavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));

        savingsAccount.deposit(1500.0);

        assertEquals(0.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new MaxiSavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);

        assertEquals(0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @Test
    public void checkingAccount1() {
        Bank bank = new Bank();
        Account checkingAccount = new CheckingAccount();
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);
        Transaction t = new Deposit(100);
        t.setDate(getTestDate(-1));
        checkingAccount.addTransaction(t);

        assertEquals(100.0*0.001/365.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savingsAccount1() {
        Bank bank = new Bank();
        Account savingsAccount = new SavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));
        Transaction t = new Deposit(100);
        t.setDate(getTestDate(-20));
        savingsAccount.addTransaction(t);

        assertEquals(Math.pow(1+0.001/365.0,20)*100-100, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @Test
    public void savingsAccount2() {
        Bank bank = new Bank();
        Account savingsAccount = new SavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));
        Transaction t = new Deposit(2000);
        t.setDate(getTestDate(-1));
        savingsAccount.addTransaction(t);

        assertEquals(1000.0 * 0.001/365.0 + 1000.0 * 0.002 / 365.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account1() {
        Bank bank = new Bank();
        Account maxiSaving = new MaxiSavingsAccount();
        bank.addCustomer(new Customer("Bill").openAccount(maxiSaving));
        Transaction t = new Deposit(2000);
        t.setDate(getTestDate(-1));
        maxiSaving.addTransaction(t);

        assertEquals(2000.0 * 0.05/365.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    //help function for testing purpose
    private Date getTestDate(int numberOfDays) {
    	Calendar c = Calendar.getInstance();
    	c.add(Calendar.DATE, numberOfDays);
        return c.getTime();
    }
    
}
