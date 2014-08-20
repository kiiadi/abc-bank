package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    private static final double DOUBLE_DELTA = 1e-15;

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new Account(Account.AccountType.CHECKING);
        Account savingsAccount = new Account(Account.AccountType.SAVINGS);

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
        Customer oscar = new Customer("Oscar").openAccount(Account.AccountType.SAVINGS);
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(Account.AccountType.SAVINGS);
        oscar.openAccount(Account.AccountType.CHECKING);
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAccounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(Account.AccountType.SAVINGS);
        oscar.openAccount(Account.AccountType.CHECKING);
        oscar.openAccount(Account.AccountType.SAVINGS);
        assertEquals(3, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTransfer(){
        Account savings = new Account(Account.AccountType.SAVINGS,1000.00);
        Account checking = new Account(Account.AccountType.SAVINGS,100.00);

        Customer john = new Customer("John", savings, checking);

        assertEquals("Savings account balance equals $1000.00", 1000.00, savings.balance(), DOUBLE_DELTA);
        assertEquals("Checking account balance equals $100.00", 100.00, checking.balance(), DOUBLE_DELTA);

        john.transfer(savings, checking, 500.00);

        assertEquals("Savings account balance equals $500.00", 500.00, savings.balance(), DOUBLE_DELTA);
        assertEquals("Checking account balance equals 5100.00", 600.00, checking.balance(), DOUBLE_DELTA);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testTransferFail(){
        Account savings = new Account(Account.AccountType.SAVINGS,1000.00);
        Account checking = new Account(Account.AccountType.SAVINGS,100.00);

        Customer john = new Customer("John", savings, checking);

        assertEquals("Savings account balance equals $1000.00", 1000.00, savings.balance(), DOUBLE_DELTA);
        assertEquals("Checking account balance equals $100.00", 100.00, checking.balance(), DOUBLE_DELTA);

        john.transfer(savings, checking, 1000.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTransferFailSameAccounts(){
        Account savings = new Account(Account.AccountType.SAVINGS,1000.00);
        Account checking = new Account(Account.AccountType.SAVINGS,100.00);

        Customer john = new Customer("John", savings, checking);

        john.transfer(savings, savings, 1000.01);
    }
}
