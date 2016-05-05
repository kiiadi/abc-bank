package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void testAccountBalances() {
        Customer greenLeaf = new Customer("GreenLeaf");
        greenLeaf.openAccount(Account.CHECKING);
        greenLeaf.deposit(100.0, Account.CHECKING);
        Account checkingAccount = greenLeaf.getAccountByType(Account.CHECKING);
        assertEquals(100.0, checkingAccount.getAccountBalance(), DOUBLE_DELTA);

        greenLeaf.openAccount(Account.SAVINGS);
        greenLeaf.deposit(100.0, Account.SAVINGS);
        Account savingsAccount = greenLeaf.getAccountByType(Account.SAVINGS);
        assertEquals(100.0, savingsAccount.getAccountBalance(), DOUBLE_DELTA);

        greenLeaf.openAccount(Account.MAXI_SAVINGS);
        greenLeaf.deposit(100.0, Account.MAXI_SAVINGS);
        Account maxiSavingsAccount = greenLeaf.getAccountByType(Account.MAXI_SAVINGS);
        assertEquals(100.0, maxiSavingsAccount.getAccountBalance(), DOUBLE_DELTA);
    }

    @Test
    public void testAccountInterests() {
        Customer ballin = new Customer("Ballin");
        ballin.openAccount(Account.CHECKING);
        ballin.deposit(100.0, Account.CHECKING);
        Account checkingAccount = ballin.getAccountByType(Account.CHECKING);
        assertEquals(0.1, checkingAccount.calculateInterest(), DOUBLE_DELTA);

        ballin.openAccount(Account.SAVINGS);
        ballin.deposit(4500.0, Account.SAVINGS);
        Account savingsAccount = ballin.getAccountByType(Account.SAVINGS);
        assertEquals(8, savingsAccount.calculateInterest(), DOUBLE_DELTA);

        ballin.openAccount(Account.MAXI_SAVINGS);
        ballin.deposit(500.0, Account.MAXI_SAVINGS);
        Account maxiSavingsAccount = ballin.getAccountByType(Account.MAXI_SAVINGS);
        assertEquals(25.0, maxiSavingsAccount.calculateInterest(), DOUBLE_DELTA);
    }

    @Test
    public void testLastWithdrawal() {
        Customer bard = new Customer("Bard");
        bard.openAccount(Account.MAXI_SAVINGS);
        bard.deposit(100.0, Account.MAXI_SAVINGS);
        bard.deposit(200.0, Account.MAXI_SAVINGS);
        bard.withdraw(100.0, Account.MAXI_SAVINGS);;
        bard.deposit(300.0, Account.MAXI_SAVINGS);
        bard.deposit(400.0, Account.MAXI_SAVINGS);
        bard.withdraw(150.0, Account.MAXI_SAVINGS);
        bard.deposit(500.0, Account.MAXI_SAVINGS);
        Account maxiSavingsAccount = bard.getAccountByType(Account.MAXI_SAVINGS);
        Transaction lastWithdrawal = maxiSavingsAccount.getLastWithdrawalTransaction();
        assertEquals(-150.0, lastWithdrawal.getAmount(), DOUBLE_DELTA);
    }
}
