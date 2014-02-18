package com.abc;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by serge on 2/14/14.
 */
public class CustomerTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test //Test customer statement generation
    public void testApp(){
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Customer henry = new Customer("Henry");
        Account checkingAccount = henry.openAccount(Account.CHECKING);
        Account savingsAccount = henry.openAccount(Account.SAVINGS);
        Account maxiAccount = henry.openAccount(Account.MAXI_SAVINGS);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);
        maxiAccount.deposit(1000);

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
                "  deposit $1,000.00\n" +
                "Total $1,000.00\n" +
                "\n" +
                "Total In All Accounts $4,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Customer oscar = new Customer("Oscar");
        Account savings = oscar.openAccount(Account.SAVINGS);
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Customer oscar = new Customer("Oscar");
        Account savings = oscar.openAccount(Account.SAVINGS);
        Account checking = oscar.openAccount(Account.CHECKING);
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Customer oscar = new Customer("Oscar");
        Account saving = oscar.openAccount(Account.SAVINGS);
        Account checking = oscar.openAccount(Account.CHECKING);
        Account maxi = oscar.openAccount(Account.MAXI_SAVINGS);
        assertEquals(3, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTotalInterestEarnedForOneYearAfterOpeningThreeAccounts(){
        System.out.println("\n===\nTesting - "+new Object(){}.getClass().getEnclosingMethod().getName());
        Customer oscar = new Customer("Oscar");
        DateProvider.getInstance().setFutureDate(-365);
        Account saving = oscar.openAccount(Account.SAVINGS);
        Account checking = oscar.openAccount(Account.CHECKING);
        Account maxi = oscar.openAccount(Account.MAXI_SAVINGS);
        assertEquals(3, oscar.getNumberOfAccounts());
        checking.deposit(1000.0);
        saving.deposit(1000.0);
        maxi.deposit(1000.0);
        assertEquals(1000.0, checking.transactions().get(0).amount, DOUBLE_DELTA);
        assertEquals(1000.0, saving.transactions().get(0).amount, DOUBLE_DELTA);
        assertEquals(1000.0, maxi.transactions().get(0).amount, DOUBLE_DELTA);
        DateProvider.getInstance().reset();
        assertEquals(1.0, checking.interestEarned(), DOUBLE_DELTA);
        assertEquals(1001.0, checking.getAccountBalance(), DOUBLE_DELTA);
        assertEquals(1.0, saving.interestEarned(), DOUBLE_DELTA);
        assertEquals(1001.0, saving.getAccountBalance(), DOUBLE_DELTA);
        assertEquals(50.0, maxi.interestEarned(), DOUBLE_DELTA);
        assertEquals(1050.0, maxi.getAccountBalance(), DOUBLE_DELTA);
        assertEquals(52.0, oscar.totalInterestEarned(), DOUBLE_DELTA);
        System.out.println("Total Interest Earned: "+oscar.totalInterestEarned());
    }
}
