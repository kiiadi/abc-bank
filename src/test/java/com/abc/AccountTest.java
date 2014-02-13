package com.abc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    Account johnsCheckingAccount;
    Account johnsSavingsAccount;
    Account johnsMaxiSavingAccount;

    @Before
    public void before() throws Exception {
        johnsCheckingAccount = new Account(AccountType.CHECKING_ACCOUNT, 100.0);
        johnsSavingsAccount = new Account(AccountType.SAVINGS_ACCOUNT, 200.0);
        johnsMaxiSavingAccount = new Account(AccountType.MAXISAVINGS_ACCOUNT, 200.0);
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void accountShouldGetTheInterestEarned() throws Exception {

        assertThat(AccountType.CHECKING_ACCOUNT.calculateInterest(100), is(0.10004987954705769));
        assertThat(AccountType.SAVINGS_ACCOUNT.calculateInterest(200), is(0.20009975909411537));

    }


    @Test
    public void depositShouldAddAmountToAccountBalance() {
        johnsCheckingAccount.deposit(100.0);
        assertThat(johnsCheckingAccount.getBalance(), is(200.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void depositShouldThrowExceptionOnZeroAmount() {
        johnsCheckingAccount.deposit(0.0);
    }

    @Test
    public void withdrawShouldDeductFromAccountBalance() throws Exception {


        johnsCheckingAccount.withdraw(50);

        assertThat(johnsCheckingAccount.getBalance(), is(50.0));
    }

    @Test
    public void transferShouldDeductFromSourceAndAddToDestination() throws Exception {

        johnsCheckingAccount.transfer(100.0, johnsSavingsAccount);

        assertThat(johnsSavingsAccount.getBalance(), is(300.0));

    }
}
