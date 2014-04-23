package com.abc.businessobjects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CustomerTest {

    private Account checkingAccount;
    private Account savingsAccount;
    private Account maxiSavingsAccount;

    @Before
    public void setUp() {
        checkingAccount = new Account(AccountType.CHECKING);
        savingsAccount = new Account(AccountType.SAVINGS);
        maxiSavingsAccount = new Account(AccountType.MAXI_SAVINGS);
    }

    @Test //Test customer statement generation
    public void testSimpleStatementApp() {
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
    public void testStatementWithInterestPaid() {
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
                "Total Interest In All Accounts $6.70", henry.getStatementWithInterestPaid());
    }

    @Test
    public void testStatementWithInterestPaidSimple() {
        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "Total $0.00\n" +
                "\n" +
                "Total Interest In All Accounts $0.10", henry.getStatementWithInterestPaid());
    }

    @Test
    public void testSimpleTransfer(){
        Customer oscar = new Customer("Oscar").openAccount(checkingAccount);
        oscar.openAccount(savingsAccount);

        checkingAccount.deposit(4000.0);
        savingsAccount.deposit(1500.0);

        assertTrue("Transfer failed!", oscar.transfer(checkingAccount, savingsAccount, 500.0));
        assertTrue("Checking amount doesn't match.", 3500.0 == checkingAccount.sumTransactions());
        assertTrue("Savings amount doesn't match.", 2000.0 == savingsAccount.sumTransactions());
    }

    @Test
    public void testTransferInsufficientFunds(){
        Customer oscar = new Customer("Oscar").openAccount(checkingAccount);
        oscar.openAccount(savingsAccount);

        checkingAccount.deposit(4000.0);
        savingsAccount.deposit(1500.0);

        assertFalse("Transfer succeeded - when it should have failed!", oscar.transfer(checkingAccount, savingsAccount, 5000.0));
        assertTrue("Checking amount doesn't match.", 4000.0 == checkingAccount.sumTransactions());
        assertTrue("Savings amount doesn't match.", 1500.0 == savingsAccount.sumTransactions());
    }

    @Test
    public void testOneAccount() {
        Customer oscar = new Customer("Oscar").openAccount(savingsAccount);
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount() {
        Customer oscar = new Customer("Oscar")
                .openAccount(savingsAccount);
        oscar.openAccount(checkingAccount);
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAccounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(savingsAccount);
        oscar.openAccount(checkingAccount);
        oscar.openAccount(maxiSavingsAccount);
        assertEquals(3, oscar.getNumberOfAccounts());
    }

    @After
    public void tearDown() {
        checkingAccount = new Account(AccountType.CHECKING);
        savingsAccount = new Account(AccountType.SAVINGS);
        maxiSavingsAccount = new Account(AccountType.MAXI_SAVINGS);
    }

}
