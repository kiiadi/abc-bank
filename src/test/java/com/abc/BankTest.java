package com.abc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {

    Bank abcBank = new Bank();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void bankShouldOpenAccount() throws Exception {
        List<Account> accountList = new LinkedList<Account>();
        accountList.add(new Account(AccountType.CHECKING_ACCOUNT, 100.0));
        accountList.add(new Account(AccountType.SAVINGS_ACCOUNT, 200.0));
        abcBank.openAccount("John", accountList);
        List<Customer> customers = abcBank.getCustomers();
        assertThat(customers.get(0).getName(), is("John"));
        assertThat(customers.get(0).getNumberOfAccounts(), is(2));
        assertThat(customers.get(0).getAccounts().get(0).getBalance(), is(100.0));
        assertThat(customers.get(0).getAccounts().get(1).getBalance(), is(200.0));
        assertThat(customers.get(0).getAccounts().get(0).getAccountType(), is(AccountType.CHECKING_ACCOUNT));
        assertThat(customers.get(0).getAccounts().get(1).getAccountType(), is(AccountType.SAVINGS_ACCOUNT));
    }

    @Test
    public void bankShouldGenerateCustomerSummary() throws Exception {
        List<Account> accountList = new LinkedList<Account>();
        accountList.add(new Account(AccountType.CHECKING_ACCOUNT, 100.0));
        accountList.add(new Account(AccountType.SAVINGS_ACCOUNT, 200.0));
        abcBank.openAccount("John", accountList);
        assertThat(abcBank.customerSummary(), is("Customer Summary\n - John ( Number of accounts are 2 )"));
    }

    @Test
    public void testTotalInterestPaid() throws Exception {
        List<Account> accountList = new LinkedList<Account>();
        accountList.add(new Account(AccountType.CHECKING_ACCOUNT, 100.0));
        accountList.add(new Account(AccountType.SAVINGS_ACCOUNT, 200.0));
        abcBank.openAccount("John", accountList);
        abcBank.openAccount("Bill", accountList);
        assertThat(abcBank.totalInterestPaid(), is(0.6002992772823461));
    }
}
