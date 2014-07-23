package com.abc;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CustomerSummaryViewTest {
    @Test
    public void shouldRenderSummaryOfAccountsForAllCustomers() {
        final Bank bank = new Bank();
        final Customer john = new Customer("John");
        john.open(AccountType.Checking);
        bank.add(john);

        assertThat(bank.view.render(bank), is("Customer Summary\n - John (1 account)"));
    }

}
