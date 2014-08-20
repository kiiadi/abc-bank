package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void testCustomerSummary() {
        Customer john = new Customer("John");
        john.openAccount(Account.AccountType.CHECKING, 100.00);
        Bank bank = new Bank(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void testCustomerCount() {
        Customer john = new Customer("John");
        john.openAccount(Account.AccountType.CHECKING, 100.00);

        Customer jim = new Customer("Jim");
        jim.openAccount(Account.AccountType.CHECKING, 100.00);

        Bank bank = new Bank(john, jim);
        assertEquals("Customer count equals 2", 2,  bank.getCustomers().size());
    }

}
