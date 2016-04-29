package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);

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
    	//can use savingsAccount directly
        Customer oscar = new Customer("Oscar").openAccount(new Account(Account.SAVINGS));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    }
    
    //test to ensure proper working of Transfer method
    @Test
    public void testTransfer(){
    	 
    	 Account checkingAccount = new Account(Account.CHECKING);
         Account savingsAccount = new Account(Account.SAVINGS);
         
         Customer Anand= new Customer("Anand").openAccount(checkingAccount).openAccount(savingsAccount);
         checkingAccount.deposit(2000);
         savingsAccount.deposit(1000);
         
         assertEquals( "Amount: "+"500.0"+" withdrawn from "+
                        checkingAccount.getAccountNumber()+
        		        " account and deposited to "+savingsAccount.getAccountNumber()
                      +" account by "+"Anand",
                      Anand.transferBetweenAccounts(checkingAccount, savingsAccount, 500.0));
         
         assertEquals(1500.0, savingsAccount.sumTransactions(),0.0);
         
    }

    @Test
    public void testThreeAcounts2() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS)).openAccount(new Account(Account.CHECKING)).openAccount(new Account(Account.MAXI_SAVINGS));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
  
    @Ignore
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
}
