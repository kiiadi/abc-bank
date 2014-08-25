package com.abc.impl;

import com.abc.api.*;
import com.abc.infra.Container;
import junit.framework.TestCase;
import org.junit.Test;

public class BankImplTest extends TestCase {

    @Test
    public void testAddCustomer() throws Exception {
        Bank bank = Container.getBank();
        Customer customer = new CustomerImpl(new CustomerIdImpl("123"), "John", "Doe");
        CustomerId customerId = bank.addCustomer(customer);
        assertEquals(true, bank.isValidCustomer(customerId));
    }

    @Test
    public void testAddAccount() throws Exception {
        Bank bank = Container.getBank();
        Customer customer = new CustomerImpl(new CustomerIdImpl("123"), "John", "Doe");
        CustomerId customerId = bank.addCustomer(customer);
        AccountId account = bank.addAccount(customer, AccountType.checking);
        assertEquals(true, bank.isValidAccount(customer, account));
    }

    public void testAddTransaction() throws Exception {

    }

    public void testAddTransfter() throws Exception {

    }
}