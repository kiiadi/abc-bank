package com.abc.bank.customer;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.abc.bank.account.AccountType;
import com.abc.bank.customer.Customer;
import com.abc.bank.customer.Customers;
import com.abc.bank.customer.CustomersImpl;
import com.abc.bank.exception.DuplicateCustomerException;

public class CustomersImplTest {

    private Customers customers;

    @Before
    public void setup(){
        customers = new CustomersImpl();
    }

    @Test(expected = DuplicateCustomerException.class)
    public void addCustomerDoesNotAllowDuplicateIds() throws DuplicateCustomerException{
        String id = UUID.randomUUID().toString();
        Customer customer1 = new Customer(id, "");
        customers.addCustomer(customer1);
        Customer customer2 = new Customer(id, "");
        customers.addCustomer(customer2);
    }

    @Test
    public void customerSummary() throws DuplicateCustomerException {
        String name = "Joe";
        Customer customer = new Customer(UUID.randomUUID().toString(),name);
        customer.openAccount(AccountType.savings);
        customers.addCustomer(customer);
        assertEquals("Customer Summary\n - " + customer.getName() + " (1 account)", customers.toString());
    }

}
