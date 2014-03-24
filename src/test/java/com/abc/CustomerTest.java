package com.abc;

import junit.framework.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.abc.AccountType.Checking;
import static com.abc.AccountType.Savings;
import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test
    public void testCustomerStatement() {

        Account checkingAccount = new Account(Checking);
        Account savingsAccount = new Account(Savings);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(new BigDecimal("100.0"));
        savingsAccount.deposit(new BigDecimal("4000.0"));
        savingsAccount.withdraw(new BigDecimal("200.0"));

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
        Customer oscar = new Customer("Oscar").openAccount(new Account(Savings));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount() {
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(new Account(Savings));
        oscar.openAccount(new Account(Checking));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test(expected = UnknownAccountException.class)
    public void testInvalidTransfer() throws UnknownAccountException {
        Customer oscar = new Customer("Oscar");
        Account checkingOscar = new Account(Checking);
        oscar.openAccount(checkingOscar);

        Customer miguel = new Customer("Miguel");
        Account checkingMiguel = new Account(Checking);
        miguel.openAccount(checkingMiguel);

        oscar.transfer(checkingOscar, checkingMiguel, BigDecimal.ONE);
    }

    @Test(expected = NegativeOrZeroAmountException.class)
    public void testZeroTransfer() throws UnknownAccountException {
        Customer oscar = new Customer("Oscar");
        Account checking  = new Account(Checking);
        oscar.openAccount(checking);
        Account savings = new Account(Savings);
        oscar.openAccount(savings);

        oscar.transfer(checking, savings, BigDecimal.ZERO);
    }

    @Test
    public void testTransfer() throws UnknownAccountException {
        Customer oscar = new Customer("Oscar");
        Account checking  = new Account(Checking);
        oscar.openAccount(checking);
        Account savings = new Account(Savings);
        oscar.openAccount(savings);

        oscar.transfer(checking, savings, BigDecimal.ONE);

        List<Transaction> checkingTransactions = checking.getTransactions();
        Assert.assertEquals(1, checkingTransactions.size());
        TestUtils.assertEquals(BigDecimal.ONE.negate(), checkingTransactions.get(0).getAmount());

        List<Transaction> savingsTransactions = savings.getTransactions();
        Assert.assertEquals(1, savingsTransactions.size());
        TestUtils.assertEquals(BigDecimal.ONE, savingsTransactions.get(0).getAmount());
    }

    @Test
    public void testTotalInterestEarned() {
        Customer oscar = new Customer("Oscar");
        Account checking  = new Account(Checking);
        oscar.openAccount(checking);
        checking.deposit(new BigDecimal(1000));
        Account checking2 = new Account(Checking);
        oscar.openAccount(checking2);
        checking.deposit(new BigDecimal(1000));
        TestUtils.assertEquals(new BigDecimal(2), oscar.totalInterestEarned());
    }
}
