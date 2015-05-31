package com.abc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1.0e-15;


    private Bank bank;
    private Customer john;

    @Before
    public void init() {
        bank = new Bank();
        john = new Customer("John");
    }

    @Test
    public void customerSummaryForOneCustomer() {
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);
        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void customerSummaryForTwoCustomers() {
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);

        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill");
        bill.openAccount(checkingAccount);
        bank.addCustomer(bill);

        assertEquals("Customer Summary\n"
                + " - John (1 account)\n"
                + " - Bill (1 account)",
                bank.customerSummary());
    }

    @Test
    public void customerSummaryForOneCustomerTwoAccounts() {
        john.openAccount(new Account(Account.CHECKING));
        john.openAccount(new Account(Account.SAVINGS));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (2 accounts)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Account checkingAccount = new Account(Account.CHECKING);
        john.openAccount(checkingAccount);
        bank.addCustomer(john);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savingsAccount() {
        Account savingsAccount = new Account(Account.SAVINGS);
        john.openAccount(savingsAccount);
        bank.addCustomer(john);

        savingsAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxiSavingsAccount() {
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
        john.openAccount(maxiSavingsAccount);
        bank.addCustomer(john);

        maxiSavingsAccount.deposit(3000.0);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
