package com.abc;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


/**
 * Tests for {@link com.abc.CustomerSummaryReportCreator}
 */
public class CustomerSummaryReportCreatorTest {

    @Test
    public void testCreate() throws Exception {
        CustomerImpl customer = new CustomerImpl("1", "John", null);
        customer.addAccount(new AccountImpl("1", AccountType.CHECKING, null, null));

        CustomerSummaryReportCreator underTest = new CustomerSummaryReportCreator();
        assertEquals("Customer Summary\n - John (1 account)", underTest.create(Arrays.asList(customer)));
    }
}
