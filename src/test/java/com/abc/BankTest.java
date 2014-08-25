package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;
    
    @Test
    public void testCustomerSummary() {
    	Bank bank= new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void testCustomerSummaryWithMultipleAccounts() {
    	Bank bank= new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        john.openAccount(new Account(Account.SAVINGS));
        
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (2 accounts)", bank.customerSummary());
    }

    
    @Test
    public void testTotalInterestPaidForCheckingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testTotalInterestPaidForsavingsAccount() {
        Bank bank = new Bank();
        Account savingsAccount = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(savingsAccount));

        savingsAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testTotalInterestPaidFormaxiSavingsAccountWithNoWithdrawals() {
        Bank bank = new Bank();
        Account maxSavingsAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(maxSavingsAccount));

        maxSavingsAccount.deposit(3000.0);

        assertEquals(150.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testTotalInterestPaidFormaxiSavingsAccountWithWithdrawals() {
        Bank bank = new Bank();
        Account maxSavingsAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(maxSavingsAccount));

        maxSavingsAccount.deposit(3000.0);
        maxSavingsAccount.withdraw(800);

        assertEquals(2.2, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    
    @Test
    public void testTotalInterestPaidForMultpleAccounts(){

        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        Account savingsAccount = new Account(Account.SAVINGS);
        bill.openAccount(savingsAccount);

        savingsAccount.deposit(1500.0);

        Account maxSavingsAccount = new Account(Account.MAXI_SAVINGS);
        Customer david = new Customer("David").openAccount(maxSavingsAccount);
        bank.addCustomer(david);

        maxSavingsAccount.deposit(3000.0);

        assertEquals(152.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    
        
    	
    }
    
    @Test
    public void testTotalInterestPaidSummary(){
    
    
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        Account savingsAccount = new Account(Account.SAVINGS);
        bill.openAccount(savingsAccount);

        savingsAccount.deposit(1500.0);

        Account maxSavingsAccount = new Account(Account.MAXI_SAVINGS);
        Customer david = new Customer("David").openAccount(maxSavingsAccount);
        bank.addCustomer(david);

        maxSavingsAccount.deposit(3000.0);
    
        assertEquals("Total Interest Summary \nTotal Interest Paid: "+152.1, bank.totalInterestSummary());    
    	
    
    }
    
    
    
}
