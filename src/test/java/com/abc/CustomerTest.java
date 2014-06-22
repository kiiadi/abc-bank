package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount).openAccount(maxiSavingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);
        maxiSavingsAccount.deposit(300.0);

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
                "Maxi Savings Account\n" +
                "  deposit $300.00\n" +
                "Total $300.00\n" +
                "\n" +
                "Total In All Accounts $4,200.00", henry.getStatement());
    }

    @Test //Test customer opening only a SAVINGS account
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new Account(Account.SAVINGS));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test //Test customer opening both savings and checking accounts
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test //Test customer opening all three types of accounts
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        oscar.openAccount(new Account(Account.MAXI_SAVINGS));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
    @Test //Test customer transfer between checking and savings account
    public void testAcccountTransfer(){
        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);

        Customer oscar = new Customer("Oscar").openAccount(checkingAccount)
                                .openAccount(savingsAccount)
                                .openAccount(maxiSavingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);
        maxiSavingsAccount.deposit(300.0);
        
        oscar.accountTransfer(savingsAccount, checkingAccount, 50.00);
        assertEquals("Statement for Oscar\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "  deposit $50.00\n" +
                "Total $150.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "  withdrawal $50.00\n" +
                "Total $3,750.00\n" +
                "\n" +
                "Maxi Savings Account\n" +
                "  deposit $300.00\n" +
                "Total $300.00\n" +
                "\n" +
                "Total In All Accounts $4,200.00", oscar.getStatement());
    }

    @Test // Test most recent withdrawal in days
    public void testWithdrawWithTenDays(){
        Account checkingAccount = new Account(Account.CHECKING);;

        Customer oscar = new Customer("Oscar").openAccount(checkingAccount);

        checkingAccount.deposit(100.0);
        checkingAccount.withdraw(50.00);
                
        assertEquals(true,oscar.getAccount(Account.CHECKING).isWithdrawDayWithinTenDays());
    }

    @Test // Test most recent withdrawal in days
    public void testNoWithdrawWithTenDays(){
        Account checkingAccount = new Account(Account.CHECKING);;

        Customer oscar = new Customer("Oscar").openAccount(checkingAccount);

        checkingAccount.deposit(100.0);
        assertEquals(false,oscar.getAccount(Account.CHECKING).isWithdrawDayWithinTenDays());
    }

}
