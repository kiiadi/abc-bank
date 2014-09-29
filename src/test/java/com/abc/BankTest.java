package com.abc;

import org.junit.Test;

import com.abc.account.IAccount;
import com.abc.customer.ICustomer;

import static org.junit.Assert.assertEquals;

public class BankTest {/*
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        ICustomer john = new ICustomer("John");
        john.openAccount(new IAccount(IAccount.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        IAccount checkingAccount = new IAccount(IAccount.CHECKING);
        ICustomer bill = new ICustomer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        IAccount checkingAccount = new IAccount(IAccount.SAVINGS);
        bank.addCustomer(new ICustomer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        IAccount checkingAccount = new IAccount(IAccount.MAXI_SAVINGS);
        bank.addCustomer(new ICustomer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

*/}
