package com.abc;

import org.junit.Test;

import com.abc.BankConstants.AccountType;

import static org.junit.Assert.assertEquals;

public class BankTest {
	@Test
	public void testInterestCalculation()
	{
	        Bank bank = new Bank();
	        Account checkingAccount = new Account(AccountType.CHECKING);
	        Customer bill = new Customer("Bill").openAccount(checkingAccount);	        
	        Account savingAccount = new Account(AccountType.SAVINGS);
	        Account maxiSavingAccount = new Account(AccountType.MAXI_SAVINGS);
	        Customer john = new Customer("John").openAccount(savingAccount).openAccount(maxiSavingAccount);
	        bank.addCustomer(bill);
	        bank.addCustomer(john);
	        checkingAccount.deposit(36500.0);
	        savingAccount.deposit(183000.0);
	        maxiSavingAccount.deposit(36500.0);
	        bank.applyInterest();
	        assertEquals(1.2, bank.totalInterestPaid(), BankTestConstants.DOUBLE_DELTA);
	        assertEquals(0.1, bill.totalInterestEarned(), BankTestConstants.DOUBLE_DELTA);
	        assertEquals(1.1, john.totalInterestEarned(), BankTestConstants.DOUBLE_DELTA);
	        assertEquals(0.1, checkingAccount.interestEarned(), BankTestConstants.DOUBLE_DELTA);
	        assertEquals(1, savingAccount.interestEarned(), BankTestConstants.DOUBLE_DELTA);
	        assertEquals(0.1, maxiSavingAccount.interestEarned(), BankTestConstants.DOUBLE_DELTA);
	 
	}
	
	
	
    @Test
    public void testCustomerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(AccountType.CHECKING));
        bank.addCustomer(john);
        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void testCheckingAccount() {
        Bank bank = new Bank();
        String customerName = "Bill";
        Account checkingAccount = new Account(AccountType.CHECKING);
        Customer bill = new Customer(customerName).openAccount(checkingAccount);
        assertEquals(customerName, checkingAccount.getCustomerName());
        bank.addCustomer(bill);
        checkingAccount.deposit(36500.0);
        checkingAccount.applyInterest();        
        assertEquals(0.1, bank.totalInterestPaid(), BankTestConstants.DOUBLE_DELTA);
    }

    @Test
    public void testSavingsAccount() {
        Bank bank = new Bank();
        Account savingAccount = new Account(AccountType.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(savingAccount));
        savingAccount.deposit(183000.0);
        savingAccount.applyInterest();
        assertEquals(1, bank.totalInterestPaid(), BankTestConstants.DOUBLE_DELTA);
    }

    @Test
    public void testMaxiSavingsAccount() {
        Bank bank = new Bank();
        Account maxiSavingAccount = new Account(AccountType.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(maxiSavingAccount));

        maxiSavingAccount.deposit(36500.0);
        maxiSavingAccount.applyInterest();

        assertEquals(0.1, bank.totalInterestPaid(), BankTestConstants.DOUBLE_DELTA);
    }

}
