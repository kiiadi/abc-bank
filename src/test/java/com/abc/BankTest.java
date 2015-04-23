package com.abc;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankTest {
	
	@Test 
	public void testBank() {
	Bank BankManager = new Bank();
	Account checkingAccount = new Account(Account.CHECKING);
    Account savingsAccount = new Account(Account.SAVINGS);
    Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
    
    Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);
    
    henry.accountDeposit(checkingAccount, 100.0);
    henry.accountDeposit(savingsAccount, 4000.0);
    henry.accountwithdraw(savingsAccount, 200.0);
    BankManager.addCustomer(henry);
    assertEquals("Customer Summary\n - Henry (2 accounts)", BankManager.customerSummary());
    assertEquals(6.7, BankManager.totalInterestPaid(), 1e-15);
    Customer oscar = new Customer("Oscar").openAccount(checkingAccount).openAccount(checkingAccount).openAccount(new Account(Account.MAXI_SAVINGS));
    oscar.accountDeposit(checkingAccount, 2500.0);
    oscar.accountDeposit(checkingAccount, 400.0);
    oscar.accountDeposit(maxiSavingsAccount, 1000.0);
    BankManager.addCustomer(oscar);
    assertEquals("Customer Summary\n - Henry (2 accounts)\n - Oscar (3 accounts)", BankManager.customerSummary());
    double mydouble = Math.round(BankManager.totalInterestPaid()*100);
    assertEquals(15.6, mydouble/100, 1e-15);
	}
  
}
