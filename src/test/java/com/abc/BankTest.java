package com.abc;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BankTest {


    @Test
    public void shouldDisplayCustomerSummary() {

        Bank bank = new Bank();
        Customer john = mock(Customer.class);
        when(john.getName()).thenReturn("John");
        when(john.getNumberOfAccounts()).thenReturn(1);

        Customer bill = mock(Customer.class);
        when(bill.getName()).thenReturn("Bill");
        when(bill.getNumberOfAccounts()).thenReturn(2);


        bank.addCustomer(john);
        bank.addCustomer(bill);

        assertEquals("Customer Summary\n - John (1 account)\n - Bill (2 accounts)", bank.customerSummary());
    }

    @Test
    public void shouldReturnCorrectTotalInterestForallCustomers() {
        Bank bank = new Bank();

        Customer bill = mock(Customer.class);
        when(bill.getName()).thenReturn("Bill");
        when(bill.totalInterestEarned()).thenReturn(10.00);


        Customer alex = mock(Customer.class);
        when(alex.getName()).thenReturn("Bill");
        when(alex.totalInterestEarned()).thenReturn(6.00);

        Customer bob = mock(Customer.class);
        when(bob.getName()).thenReturn("Bill");
        when(bob.totalInterestEarned()).thenReturn(0.1);

        bank.addCustomer(bill);
        bank.addCustomer(alex);
        bank.addCustomer(bob);

        Assert.assertThat(bank.totalInterestPaidAllCustomers(), CoreMatchers.is(16.1));
    }



}
