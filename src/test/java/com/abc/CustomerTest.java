package com.abc;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {
	private static final double DOUBLE_DELTA = 1e-15;

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0, null);
        savingsAccount.deposit(4000.0, null);
        savingsAccount.withdraw(200.0, null);
        
        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "  Interest Earned $0.00\n" +
                "Total Balance $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "  Interest Earned $0.00\n" +
                "Total Balance $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }
  

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new Account(Account.SAVINGS));
        assertEquals(1, oscar.getNumberOfAccounts());
        assertEquals(Account.SAVINGS, oscar.getAccounts().get(0).getAccountType());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
        assertEquals(Account.SAVINGS, oscar.getAccounts().get(0).getAccountType());
        assertEquals(Account.CHECKING, oscar.getAccounts().get(1).getAccountType());
    }

    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        oscar.openAccount(new Account(Account.MAXI_SAVINGS));
        assertEquals(Account.SAVINGS, oscar.getAccounts().get(0).getAccountType());
        assertEquals(Account.CHECKING, oscar.getAccounts().get(1).getAccountType());
        assertEquals(Account.MAXI_SAVINGS, oscar.getAccounts().get(2).getAccountType());
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testTransferInvalidAmount(){
    	Account a = new Account(Account.SAVINGS);
    	Account b = new Account(Account.CHECKING);
        Customer oscar = new Customer("Oscar").openAccount(a);
        oscar.openAccount(b);
        //test over withdraw
       	oscar.Transfer(a, b, 1000);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testTransferInvalidAccount(){
    	Account a = new Account(Account.SAVINGS);
    	Account b = new Account(Account.CHECKING);
        Customer oscar = new Customer("Oscar").openAccount(a);
        
        //b is not customer's account
       	oscar.Transfer(a, b, 1000);
    }
    
    @Test
    public void testTransfer(){
    	Account a = new Account(Account.SAVINGS);
    	Account b = new Account(Account.CHECKING);
        Customer oscar = new Customer("Oscar").openAccount(a);
        oscar.openAccount(b);
        a.deposit(2000, null);
        oscar.Transfer(a, b, 1000);
        assertEquals(1000.0, b.sumTransactions(), DOUBLE_DELTA);
        assertEquals(1000.0, a.sumTransactions(), DOUBLE_DELTA);
    }
}
