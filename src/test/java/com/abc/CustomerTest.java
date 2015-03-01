package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    private static final double DOUBLE_DELTA = 1e-15;

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingsAccount();

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(new BigDecimal(100.0));
        savingsAccount.deposit(new BigDecimal(4000.0));
        savingsAccount.withdraw(new BigDecimal(200.0));

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
        Customer oscar = new Customer("Oscar").openAccount(new SavingsAccount());
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Ignore
    public void testThreeAccounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        assertEquals(3, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTransfer() {
        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingsAccount();
        Account maxiSavingsAccount = new MaxiSavingsAccount();

        Customer oscar = new Customer("Oscar");
        oscar.openAccount(checkingAccount);
        oscar.openAccount(savingsAccount);
        oscar.openAccount(maxiSavingsAccount);

        checkingAccount.deposit(new BigDecimal(1000.0));
        savingsAccount.deposit(new BigDecimal(1000.0));
        maxiSavingsAccount.deposit(new BigDecimal(1000.0));

        oscar.transfer(checkingAccount, maxiSavingsAccount, new BigDecimal(800.0));
        oscar.transfer(savingsAccount, maxiSavingsAccount, new BigDecimal(500.0));
        assertEquals(200.0, checkingAccount.sumTransactions().doubleValue(), DOUBLE_DELTA);
        assertEquals(500.0, savingsAccount.sumTransactions().doubleValue(), DOUBLE_DELTA);
        assertEquals(2300.0, maxiSavingsAccount.sumTransactions().doubleValue(), DOUBLE_DELTA);
    }

}
