package com.abc;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CustomerTest {

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

        assertThat(customer.totalInterestEarned(), equalTo(2.1));
    }
}
