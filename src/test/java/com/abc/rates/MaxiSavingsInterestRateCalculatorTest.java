package com.abc.rates;

import com.abc.Account;
import com.abc.AccountType;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxiSavingsInterestRateCalculatorTest {

    @Test
    public void shouldHaveAFlatRateOfTwoPercentForTheFirstThousandDollars() throws Exception {
        final double[] amounts = {0.01, 0.02, 0.04, 0.05, 0.07, 0.08, 0.10, 1.00, 1000.00};
        for (double amount : amounts) {
            final Account account = openMaxiSavings();
            account.deposit(amount);
            assertThat("Invalid interest earned after depositing " + amount, account.interestEarned() / 0.02, is(account.currentBalance()));
        }
    }

    @Test
    public void shouldHaveAFlatRateOfTwoPercentForTheFirstThousandDollars_FloatingPointEdgeCases() throws Exception {
        final Account three = openMaxiSavings();
        three.deposit(0.03);
        assertThat(three.interestEarned(), is(0.0006));

        final Account six = openMaxiSavings();
        six.deposit(0.06);
        assertThat(six.interestEarned(), is(0.0012));

        final Account nine = openMaxiSavings();
        nine.deposit(0.09);
        assertThat(nine.interestEarned(), is(0.0018));
    }

    @Test
    public void shouldHaveAFlatRateOfFivePercentForTheSecondThousandDollars() throws Exception {
        final double[] amounts = {1000.01, 2000.00};
        for (double amount : amounts) {
            final Account account = openMaxiSavings();
            account.deposit(amount);
            assertThat("Invalid interest earned after depositing " + amount, ((account.interestEarned() - 20) / 0.05) + 1000.00, is(account.currentBalance()));
        }
    }

    @Test
    public void shouldHaveAFlatRateOf10PercentAfterTheSecondThousandDollars() throws Exception {
        final double[] amounts = {2000.01, 1000000.00};
        for (double amount : amounts) {
            final Account account = openMaxiSavings();
            account.deposit(amount);
            assertThat("Invalid interest earned after depositing " + amount, ((account.interestEarned() - 70) / 0.1) + 2000.00, is(account.currentBalance()));
        }
    }

    private Account openMaxiSavings() {
        return new Account(AccountType.MaxiSavings);
    }
}
