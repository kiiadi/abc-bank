package com.abc.unittests;

import com.abc.impl.DefaultAccountManager;
import com.abc.model.api.AccountManager;
import com.abc.model.entity.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class AccountLifecycleTest {

    private AccountManager accountManager = new DefaultAccountManager();

    @Test
    public void addNewCheckingAccount() {
        Customer customer = new Customer("Customer 1");
        String accountName = "Checking Account";
        accountManager.openCheckingAccount(customer,accountName);

        assertEquals(1, customer.getAccounts().size());
        Account newAccount = customer.getAccounts().get(0);
        assertEquals(CheckingAccount.class, newAccount.getClass());
        assertEquals(accountName, newAccount.getName());
    }

    @Test
    public void addNewSavingsAccount() {
        Customer customer = new Customer("Customer 1");
        String accountName = "Savings Account";
        accountManager.openSavingsAccount(customer, accountName);

        assertEquals(1, customer.getAccounts().size());
        Account newAccount = customer.getAccounts().get(0);
        assertEquals(SavingsAccount.class, newAccount.getClass());
        assertEquals(accountName, newAccount.getName());
    }

    @Test
    public void addNewMaxiSavingsAccount() {
        Customer customer = new Customer("Customer 1");
        String accountName = "Maxi Savings Account";
        accountManager.openMaxiSavingsAccount(customer,accountName);

        assertEquals(1, customer.getAccounts().size());
        Account newAccount = customer.getAccounts().get(0);
        assertEquals(MaxiSavingsAccount.class, newAccount.getClass());
        assertEquals(accountName, newAccount.getName());
    }


}
