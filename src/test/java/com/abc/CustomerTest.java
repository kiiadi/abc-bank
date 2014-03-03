package com.abc;

import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;
import com.abc.account.exception.InsufficientFundsException;
import java.math.BigDecimal;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CustomerTest {

    @Test
    public void statement() {

        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingsAccount();
        Account maxiSavingsAccount = new MaxiSavingsAccount();

        Customer henry = new Customer("Henry");
        henry.openAccount(checkingAccount);
        henry.openAccount(savingsAccount);
        henry.openAccount(maxiSavingsAccount);

        checkingAccount.deposit(new BigDecimal("100.0"));

        try {
            checkingAccount.withdraw(new BigDecimal("50.0"));
        } catch (InsufficientFundsException ife) {
            fail("Check withdraw logic amount does exist for withdrawal.");
        }

        savingsAccount.deposit(new BigDecimal("4000.0"));
        savingsAccount.deposit(new BigDecimal("500.0"));
        try {
            savingsAccount.withdraw(new BigDecimal("200.0"));
        } catch (InsufficientFundsException ife) {
            fail("Check withdraw logic amount does exist for withdrawal.");
        }

        maxiSavingsAccount.deposit(new BigDecimal("300.0"));
        maxiSavingsAccount.deposit(new BigDecimal("200.0"));
        try {
            maxiSavingsAccount.withdraw(new BigDecimal("40.0"));
        } catch (InsufficientFundsException ife) {
            fail("Check withdraw logic amount does exist for withdrawal.");
        }
        try {
            maxiSavingsAccount.withdraw(new BigDecimal("45.0"));
        } catch (InsufficientFundsException ife) {
            fail("Check withdraw logic amount does exist for withdrawal.");
        }

        System.out.println(henry.getStatement());

        assertEquals("Statement for Henry\n"
                + "\n"
                + "Checking Account\n"
                + " deposit $100.00\n"
                + " withdrawal ($50.00)\n"
                + "Total $50.00"
                + "\n"
                + "\n"
                + "Savings Account\n"
                + " deposit $4,000.00\n"
                + " deposit $500.00\n"
                + " withdrawal ($200.00)\n"
                + "Total $4,300.00"
                + "\n"
                + "\n"
                + "Maxi Savings Account\n"
                + " deposit $300.00\n"
                + " deposit $200.00\n"
                + " withdrawal ($40.00)\n"
                + " withdrawal ($45.00)\n"
                + "Total $415.00"
                + "\n"
                + "\nTotal In All Accounts $4,765.00", henry.getStatement());
    }

    @Test
    public void interestEarned() {

        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingsAccount();
        Account maxiSavingsAccount = new MaxiSavingsAccount();

        Customer henry = new Customer("Henry");
        henry.openAccount(checkingAccount);
        henry.openAccount(savingsAccount);
        henry.openAccount(maxiSavingsAccount);

        maxiSavingsAccount.deposit(new BigDecimal("4000.00"));
        assertEquals(new BigDecimal("200.00"), maxiSavingsAccount.interestEarned());

        checkingAccount.deposit(new BigDecimal("1536.00"));
        assertEquals(new BigDecimal("1.54"), checkingAccount.interestEarned());

        savingsAccount.deposit(new BigDecimal("1000.00"));
        assertEquals(new BigDecimal("1.00"), savingsAccount.interestEarned());

        assertEquals(new BigDecimal("202.54"), henry.totalInterestEarned());
    }

    @Test
    public void oneAccount() {
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(new SavingsAccount());
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void twoAccount() {
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void threeAcounts() {
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        oscar.openAccount(new MaxiSavingsAccount());

        assertEquals(3, oscar.getNumberOfAccounts());
    }

    @Test
    public void rogueCustomer() {

        Customer rogue = null;

        try {
            rogue = new Customer(null);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

    }

    @Test
    public void openAccountRogue() {

        Customer oscar = new Customer("Oscar");

        try {
            oscar.openAccount(null);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

    }

    @Test
    public void balanceTransfer() {

        boolean caught = false;

        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingsAccount();

        Customer henry = new Customer("Henry");
        henry.openAccount(checkingAccount);
        henry.openAccount(savingsAccount);

        checkingAccount.deposit(new BigDecimal("5000.00"));
        savingsAccount.deposit(new BigDecimal("1000.00"));

        try {
            henry.balanceTransfer(checkingAccount, savingsAccount, new BigDecimal("1000.00"));
        } catch (InsufficientFundsException isf) {
            fail();
        }

        assertEquals(new BigDecimal("4000.00"), checkingAccount.balance());
        assertEquals(new BigDecimal("2000.00"), savingsAccount.balance());

        // excess amount transfer
        try {
            henry.balanceTransfer(savingsAccount, checkingAccount, new BigDecimal("3000.00"));
        } catch (InsufficientFundsException isf) {
            caught = true;
        } finally {
            assertTrue(caught);
        }

        caught = false;
        // transfer in same account
        try {
            henry.balanceTransfer(savingsAccount, savingsAccount, new BigDecimal("2000.00"));
        } catch (IllegalArgumentException isf) {
            caught = true;
        } catch (InsufficientFundsException isf) {
            fail("Unexpected.");
        } finally {
            assertTrue(caught);
        }

    }
}
