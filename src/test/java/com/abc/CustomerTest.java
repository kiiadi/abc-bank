package com.abc;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CustomerTest {

    @Test
    public void shouldKnowTheTotalNumberOfAccounts() {
        final Customer oscar = new Customer("Oscar");
        for (int i = 1; i < 4; i++) {
            oscar.open(AccountType.Savings);
            assertThat(oscar.getNumberOfAccounts(), is(i));
        }

    }

    @Test
    public void shouldAllowTransfersBetweenAccounts() throws Exception {
        final Customer customer = new Customer("Kyle Gas");
        final Account from = customer.open(AccountType.Checking);
        from.deposit(1000.00);

        final Account to = customer.open(AccountType.Savings);

        customer.transfer(from, to, 200.00);

        assertThat(from.currentBalance(), Matchers.is(800.00));
        assertThat(to.currentBalance(), Matchers.is(200.00));
        assertThat(customer.totalBalance(), Matchers.is(1000.00));
    }

    @Test
    public void shouldAllowTransfersEvenWhenAccountsAreOverdrawn() throws Exception {
        final Customer customer = new Customer("Kyle Gas");
        final Account from = customer.open(AccountType.Checking);

        final Account to = customer.open(AccountType.Savings);

        customer.transfer(from, to, 200.00);

        assertThat(from.currentBalance(), Matchers.is(-200.00));
        assertThat(to.currentBalance(), Matchers.is(200.00));
        assertThat(customer.totalBalance(), Matchers.is(0.00));
    }

}
