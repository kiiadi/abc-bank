package com.abc;

import com.abc.account.CheckingAccount;
import com.abc.account.SavingsAccount;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    private static final double DOUBLE_DELTA = 1e-15;

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingsAccount();

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.03", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(Account.AccountType.SAVINGS, 100.00);
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(Account.AccountType.SAVINGS, 100.00);
        oscar.openAccount(Account.AccountType.CHECKING, 100.00);
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAccounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(Account.AccountType.SAVINGS, 100.00);
        oscar.openAccount(Account.AccountType.CHECKING, 100.00);
        oscar.openAccount(Account.AccountType.SAVINGS, 100.00);
        assertEquals(3, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTransfer(){
        Account savings = new SavingsAccount(1000.00);
        Account checking = new CheckingAccount(100.00);

        Customer john = new Customer("John", savings, checking);

        assertEquals("Savings account balance equals $1000.00", 1000.00, savings.sumTransactions(), DOUBLE_DELTA);
        assertEquals("Checking account balance equals $100.00", 100.00, checking.sumTransactions(), DOUBLE_DELTA);

        john.transfer(savings, checking, 500.00);

        assertEquals("Savings account balance equals $500.00", 500.00, savings.sumTransactions(), DOUBLE_DELTA);
        assertEquals("Checking account balance equals 5100.00", 600.00, checking.sumTransactions(), DOUBLE_DELTA);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testTransferFail(){
        Account savings = new SavingsAccount(1000.00);
        Account checking = new CheckingAccount(100.00);

        Customer john = new Customer("John", savings, checking);

        assertEquals("Savings account balance equals $1000.00", 1000.00, savings.sumTransactions(), DOUBLE_DELTA);
        assertEquals("Checking account balance equals $100.00", 100.00, checking.sumTransactions(), DOUBLE_DELTA);

        john.transfer(savings, checking, 1000.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTransferFailSameAccounts(){
        Account savings = new SavingsAccount(1000.00);
        Account checking = new CheckingAccount(100.00);

        Customer john = new Customer("John", savings, checking);

        john.transfer(savings, savings, 1000.01);
    }
}
