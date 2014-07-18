package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CustomerTest {

    private static final double DOUBLE_DELTA = 1e-15;

    @Test //Test customer statement generation
    public void testApp() {

        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingsAccount();

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        henry.transferFunds(null, checkingAccount, 100.0);
        henry.transferFunds(null, savingsAccount, 4000.0);
        henry.transferFunds(savingsAccount, null, 200.0);

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
    public void testOneAccount() {
        Customer oscar = new Customer("Oscar").openAccount(new SavingsAccount());
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount() {
        Customer oscar = new Customer("Oscar").openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar").openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        oscar.openAccount(new MaxiSavingsAccount());
        assertEquals(3, oscar.getNumberOfAccounts());
    }

    @Test
    public void shouldTransferFunds() {
        Customer oscar = new Customer("Oscar");
        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingsAccount();
        Account maxiSavingsAccount = new MaxiSavingsAccount();

        oscar.openAccount(checkingAccount);
        oscar.openAccount(savingsAccount);
        oscar.openAccount(maxiSavingsAccount);

        // deposit to checking account
        oscar.transferFunds(null, checkingAccount, 1000.0);
        // transfer some funds to maxi-savings account
        oscar.transferFunds(checkingAccount, maxiSavingsAccount, 500.0);
        oscar.transferFunds(checkingAccount, savingsAccount, 200.0);

        assertEquals(300.0, checkingAccount.sumTransactions(), DOUBLE_DELTA);
        assertEquals(200.0, savingsAccount.sumTransactions(), DOUBLE_DELTA);
        assertEquals(500.0, maxiSavingsAccount.sumTransactions(), DOUBLE_DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException() {
        Customer oscar = new Customer("Oscar");
        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingsAccount();
        Account maxiSavingsAccount = new MaxiSavingsAccount();

        oscar.openAccount(checkingAccount);
        oscar.openAccount(savingsAccount);

        // deposit to checking account
        oscar.transferFunds(null, checkingAccount, 1000.0);
        oscar.transferFunds(checkingAccount, maxiSavingsAccount, 200.0);
        fail();
    }
}
