package com.abc;

import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    private static final double DOUBLE_DELTA = 1.0e-15;

    private Customer customer;

    @Before
    public void init() {
        customer = new Customer("Henry");
    }

    @Test
    public void openOneAccount(){
        customer.openAccount(new SavingsAccount());
        assertEquals(1, customer.getAccounts().size());
    }

    @Test
    public void openTwoAccounts() {
        customer.openAccount(new SavingsAccount());
        customer.openAccount(new CheckingAccount());
        assertEquals(2, customer.getAccounts().size());
    }

    @Test
    public void openThreeAccounts() {
        customer.openAccount(new SavingsAccount());
        customer.openAccount(new CheckingAccount());
        customer.openAccount(new MaxiSavingsAccount());
        assertEquals(3, customer.getAccounts().size());
    }

    @Test
    public void totalInterestEarned() {
        Account checkingAccount = new CheckingAccount();
        customer.openAccount(checkingAccount);
        checkingAccount.deposit(100.0);

        Account savingsAccount = new SavingsAccount();
        customer.openAccount(savingsAccount);
        savingsAccount.deposit(1500.0);

        assertEquals(2.1, customer.totalInterestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void transfer() {
        Account checkingAccount = new CheckingAccount();
        customer.openAccount(checkingAccount);
        checkingAccount.deposit(100.0);

        Account savingsAccount = new SavingsAccount();
        customer.openAccount(savingsAccount);

        customer.transfer(10.0, checkingAccount, savingsAccount);

        assertThat(checkingAccount.getBalance(), equalTo(90.0));
        assertThat(savingsAccount.getBalance(), equalTo(10.0));
    }

    @Test(expected = IllegalStateException.class)
    public void transferFromInvalidAccount() {
        Account savingsAccount = new SavingsAccount();
        customer.openAccount(savingsAccount);
        customer.transfer(50.0, new CheckingAccount(), savingsAccount);
    }

    @Test(expected = IllegalStateException.class)
    public void transferToInvalidAccount() {
        Account checkingAccount = new CheckingAccount();
        customer.openAccount(checkingAccount);
        customer.transfer(50.0, checkingAccount, new SavingsAccount());
    }

}
