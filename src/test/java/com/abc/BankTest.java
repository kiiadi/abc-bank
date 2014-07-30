package com.abc;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;


    @Test
    public void shouldComputeTotalInterestPaidForFlatRateAccounts() {
        final Bank bank = new Bank();
        final Customer bill = new Customer("Bill");
        final Account account = bill.open(AccountType.Checking);
        bank.add(bill);

        account.deposit(100.0);

        assertThat(bank.totalInterestPaid(), closeTo(0.1, DOUBLE_DELTA));
    }

    @Test
    public void shouldComputeTotalInterestPaidForSavingsAccounts() {
        final Bank bank = new Bank();
        final Customer customer = new Customer("Bill");
        final Account account = customer.open(AccountType.Savings);
        bank.add(customer);

        account.deposit(1500.0);

        assertThat(bank.totalInterestPaid(), closeTo(2.0, DOUBLE_DELTA));
    }

    @Test
    public void shouldComputeTotalInterestPaidForMaxiSavingsAccounts() {
        final Bank bank = new Bank();
        final Customer customer = new Customer("Bill");
        final Account account = customer.open(AccountType.MaxiSavings);
        bank.add(customer);

        account.deposit(3000.0);

        assertThat(bank.totalInterestPaid(), closeTo(170.0, DOUBLE_DELTA));
    }

}
