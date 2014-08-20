package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void testCustomerSummary() {
        Customer john = new Customer("John");
        john.openAccount(Account.AccountType.CHECKING);
        Bank bank = new Bank(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void testCustomerCount() {
        Customer john = new Customer("John");
        john.openAccount(Account.AccountType.CHECKING);

        Customer jim = new Customer("Jim");
        jim.openAccount(Account.AccountType.CHECKING);

        Bank bank = new Bank(john, jim);
        assertEquals("Customer count equals 2", 2,  bank.getCustomers().size());
    }

    @Test
    public void testCheckingAccount() {
        Bank bank = new Bank();
        Customer bill = new Customer("Bill").openAccount(Account.AccountType.CHECKING, 100.00);
        bank.addCustomer(bill);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testSavingsAccount() {
        Bank bank = new Bank();
        bank.addCustomer(new Customer("Bill").openAccount(Account.AccountType.SAVINGS, 1500.00));

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testMaxiSavingsAccount() {
        Bank bank = new Bank();
        bank.addCustomer(new Customer("Bill").openAccount(Account.AccountType.MAXI_SAVINGS, 3000.00));

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
