package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeDeposit() {
        Account checkingsAccount = new Account(Account.CHECKING);
        Customer cust = new Customer("Bob").openAccount(checkingsAccount);

        checkingsAccount.deposit(-100.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeWithdraw() {
        Account checkingsAccount = new Account(Account.CHECKING);
        Customer cust = new Customer("Bob").openAccount(checkingsAccount);

        checkingsAccount.withdraw(-100.0);
    }

    @Test
    public void testCustomerDepositAndWithdraw() {
        Account checkingsAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);
        Customer cust = new Customer("Claire").openAccount(checkingsAccount)
                .openAccount(savingsAccount);

        cust.deposit(100.0, checkingsAccount.getAccountNumber());
        cust.deposit(4000.0, savingsAccount.getAccountNumber());
        cust.withdraw(200.0, savingsAccount.getAccountNumber());
        cust.withdraw(50.0, checkingsAccount.getAccountNumber());

        assertEquals("Statement for Claire\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "  withdrawal $50.00\n" +
                "Total $50.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,850.00", cust.getStatement());
    }

    @Test
    public void testOneAccount(){
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

    @Test
    public void testTransfer() {
        Account checkingsAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);
        Customer cust = new Customer("David").openAccount(checkingsAccount)
                .openAccount(savingsAccount);

        cust.deposit(100.0, checkingsAccount.getAccountNumber());
        cust.deposit(4000.0, savingsAccount.getAccountNumber());
        cust.withdraw(200.0, savingsAccount.getAccountNumber());
        cust.withdraw(50.0, checkingsAccount.getAccountNumber());
        cust.transferBetweenAccounts(50, savingsAccount.getAccountNumber(), checkingsAccount.getAccountNumber());

        assertEquals("Statement for David\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "  withdrawal $50.00\n" +
                "  deposit $50.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "  withdrawal $50.00\n" +
                "Total $3,750.00\n" +
                "\n" +
                "Total In All Accounts $3,850.00", cust.getStatement());
    }

    @Ignore
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
}
