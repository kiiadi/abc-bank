package com.abc;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.containsString;

public class AccountTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldPreventWithdrawalOfZeroDollarAmounts() throws Exception {
        assertInvalidWithdrawalAmount(0);
    }

    @Test
    public void shouldPreventWithdrawalOfNegativeDollarAmounts() throws Exception {
        assertInvalidWithdrawalAmount(-0.01d);
    }

    @Test
    public void shouldPreventDepositsOfZeroDollarAmounts() throws Exception {
        assertInvalidDepositAmount(0);
    }

    @Test
    public void shouldPreventDepositsOfNegativeDollarAmounts() throws Exception {
        assertInvalidDepositAmount(-0.01d);
    }

    private void assertInvalidDepositAmount(final double amount) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(containsString("greater than zero"));
        final Account account = new Account(AccountType.Checking);
        account.deposit(amount);
    }

    private void assertInvalidWithdrawalAmount(final double amount) {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(containsString("greater than zero"));
        final Account account = new Account(AccountType.Checking);
        account.withdraw(amount);
    }

}
