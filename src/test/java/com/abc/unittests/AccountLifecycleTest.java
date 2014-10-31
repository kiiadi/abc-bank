package com.abc.unittests;

import com.abc.impl.manager.DefaultAccountManager;
import com.abc.model.api.AccountManager;
import com.abc.model.entity.CheckingAccount;
import com.abc.model.entity.Customer;
import com.abc.model.entity.MaxiSavingsAccount;
import com.abc.model.entity.SavingsAccount;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public class AccountLifecycleTest {

    private AccountManager accountManager = new DefaultAccountManager();

    @Test
    public void addNewCheckingAccount() {
        Customer customer = new Customer("Customer 1");
        accountManager.openCheckingAccount(customer);

        Assert.assertEquals(1,customer.getAccounts().size());
        Assert.assertEquals(CheckingAccount.class,customer.getAccounts().get(0).getClass());
    }

    @Test
    public void addNewSavingsAccount() {
        Customer customer = new Customer("Customer 1");
        accountManager.openSavingsAccount(customer);

        Assert.assertEquals(1,customer.getAccounts().size());
        Assert.assertEquals(SavingsAccount.class,customer.getAccounts().get(0).getClass());
    }

    @Test
    public void addNewMaxiSavingsAccount() {
        Customer customer = new Customer("Customer 1");
        accountManager.openMaxiSavingsAccount(customer);

        Assert.assertEquals(1,customer.getAccounts().size());
        Assert.assertEquals(MaxiSavingsAccount.class,customer.getAccounts().get(0).getClass());
    }


}
