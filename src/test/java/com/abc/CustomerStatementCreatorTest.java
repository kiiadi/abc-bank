package com.abc;

import org.junit.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;

/**
 * Tests for {@link com.abc.CustomerStatementCreator}
 */
public class CustomerStatementCreatorTest {
    @Test
    public void testCreate() throws Exception {
        CustomerStatementCreator underTest = new CustomerStatementCreator();

        CustomerImpl customer = new CustomerImpl("1", "name", null);

        AccountImpl account1 = Mockito.mock(AccountImpl.class);
        Mockito.when(account1.getStatement()).thenReturn("s1");
        Mockito.when(account1.getBalance()).thenReturn(new BigDecimal("100.00"));
        customer.addAccount(account1);

        AccountImpl account2 = Mockito.mock(AccountImpl.class);
        Mockito.when(account2.getStatement()).thenReturn("s2");
        Mockito.when(account2.getBalance()).thenReturn(new BigDecimal("-50.00"));
        customer.addAccount(account2);

        assertEquals("Statement for name\n" +
            "\n" +
            "s1\n" +
            "\n" +
            "s2\n" +
            "\n" +
            "Total In All Accounts $50.00",
            underTest.create(customer));
    }
}
