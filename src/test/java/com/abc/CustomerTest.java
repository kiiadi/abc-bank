package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new Account(AccountType.Checking);
        Account savingsAccount = new Account(AccountType.Savings);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new Account(AccountType.Savings));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(AccountType.Savings));
        oscar.openAccount(new Account(AccountType.Checking));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(AccountType.Savings));
        oscar.openAccount(new Account(AccountType.Checking));
        oscar.openAccount(new Account(AccountType.Max_Savings));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
    @Test
    public void testTransfer(){
    	 Account checkingAccount = new Account(AccountType.Checking);
         Account savingsAccount = new Account(AccountType.Savings);

         Customer oscar = new Customer("Oscar").openAccount(checkingAccount).openAccount(savingsAccount);

         checkingAccount.deposit(100.0);
         oscar.transfer(checkingAccount.getAccountType(), savingsAccount.getAccountType(), 20.0);
        
         assertEquals(checkingAccount.sumTransactions(), 80.0, 0.01);
         assertEquals(savingsAccount.sumTransactions(), 20, 0.01);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testTransferMissingAccount(){
    	 Account checkingAccount = new Account(AccountType.Checking);
         Account savingsAccount = new Account(AccountType.Savings);

         Customer oscar = new Customer("Oscar").openAccount(checkingAccount);
         oscar.transfer(checkingAccount.getAccountType(), savingsAccount.getAccountType(), 20.0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testTransferOverdraw(){
    	 Account checkingAccount = new Account(AccountType.Checking);
         Account savingsAccount = new Account(AccountType.Savings);

         Customer oscar = new Customer("Oscar").openAccount(checkingAccount).openAccount(savingsAccount);
         checkingAccount.deposit(100.0);
         oscar.transfer(checkingAccount.getAccountType(), savingsAccount.getAccountType(), 200.0);
    }
}
