package com.abc;

import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static junit.framework.Assert.assertEquals;

/**
 * Tests for {@link com.abc.AccountStatementCreator}
 */
public class AccountStatementCreatorTest {

    @Test
    public void testCreate() {
        AccountImpl account = Mockito.mock(AccountImpl.class);
        Transaction transaction = new Transaction(BigDecimal.TEN, DateUtils.getDate(2014, 1, 1), BigDecimal.TEN);
        Transaction transaction2 = new Transaction(BigDecimal.ONE.negate(), DateUtils.getDate(2014, 1, 1), new BigDecimal("9.00"));

        AccountStatementCreator underTest = new AccountStatementCreator();
        when(account.getTransactions()).thenReturn(Arrays.asList(transaction, transaction2));
        when(account.getAccountType()).thenReturn(AccountType.SAVINGS);
        when(account.getBalance()).thenReturn(new BigDecimal("9.00"));

        assertEquals("Savings\n" +
            "deposit $10.00\n" +
            "withdrawal $1.00\n" +
            "Total $9.00",
            underTest.create(account));
    }
}
