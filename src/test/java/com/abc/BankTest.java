package com.abc;

import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link com.abc.Bank}
 */
public class BankTest {

    @Test
    public void addTwoCustomers() {
        CustomerImpl customer = new CustomerImpl("1", "John", null);
        CustomerImpl customer2 = new CustomerImpl("2", "Fred", null);
        Bank bank = new Bank();
        bank.addCustomer(customer);
        bank.addCustomer(customer2);

        assertEquals(customer, bank.getCustomer("1"));
        assertEquals(customer2, bank.getCustomer("2"));

        List<Customer> customers = bank.getCustomers();
        assertEquals(2, customers.size());
        assertTrue(customers.contains(customer));
        assertTrue(customers.contains(customer2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addCustomer_WhenAlreadyExists_ThrowsException()  {
        CustomerImpl customer = new CustomerImpl("1", "John", null);
        Bank bank = new Bank();
        bank.addCustomer(customer);
        bank.addCustomer(customer);

        List<Customer> customers = bank.getCustomers();
        assertEquals(1, customers.size());
        assertTrue(customers.contains(customer));
    }

    @Test
    public void getTotalInterestPaid_ForTwoCustomers()  {
        BigDecimal interest1 = new BigDecimal("100.00");
        BigDecimal interest2 = new BigDecimal("50.00");

        Date date = DateUtils.getDate(2014, 1, 1);

        CustomerImpl customer = Mockito.mock(CustomerImpl.class);
        Mockito.when(customer.getId()).thenReturn("1");
        Mockito.when(customer.getTotalInterestEarned(date)).thenReturn(interest1);

        CustomerImpl customer2 = Mockito.mock(CustomerImpl.class);
        Mockito.when(customer.getId()).thenReturn("2");
        Mockito.when(customer2.getTotalInterestEarned(date)).thenReturn(interest2);

        Bank bank = new Bank();
        bank.addCustomer(customer);
        bank.addCustomer(customer2);

        assertEquals(new BigDecimal("150.00"), bank.getTotalInterestPaid(date));
    }
}
