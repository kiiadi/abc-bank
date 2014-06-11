package com.abc;

import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link com.abc.CustomerImpl}
 */
public class CustomerImplTest {

    @Test
    public void addTwoAccounts() {
        AccountImpl account = Mockito.mock(AccountImpl.class);
        AccountImpl account2 = Mockito.mock(AccountImpl.class);

        CustomerImpl underTest = new CustomerImpl("1", "name", null);

        underTest.addAccount(account);
        underTest.addAccount(account2);

        assertEquals(2, underTest.getAccounts().size());
        assertTrue(underTest.getAccounts().contains(account));
        assertTrue(underTest.getAccounts().contains(account2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTwoAccounts_WhenSecondIsSameAsFirst() {
        AccountImpl account = Mockito.mock(AccountImpl.class);

        CustomerImpl underTest = new CustomerImpl("1", "name", null);

        underTest.addAccount(account);
        underTest.addAccount(account);
    }

    @Test
    public void getIdAndName() {
        String id = "1";
        String name = "name";
        CustomerImpl underTest = new CustomerImpl(id, name, null);

        assertEquals(name, underTest.getName());
        assertEquals(id, underTest.getId());
    }

    @Test
    public void getTotalInterestEarned() {
        Date date = DateUtils.getDate(2014, 1, 1);
        CustomerImpl underTest = new CustomerImpl("1", "name", null);

        AccountImpl account = Mockito.mock(AccountImpl.class);
        Mockito.when(account.getInterestEarned(date)).thenReturn(new BigDecimal("1.00"));
        underTest.addAccount(account);

        AccountImpl account2 = Mockito.mock(AccountImpl.class);
        Mockito.when(account2.getInterestEarned(date)).thenReturn(new BigDecimal("2.50"));
        underTest.addAccount(account2);

        assertEquals(new BigDecimal("3.50"), underTest.getTotalInterestEarned(date));
    }

    @Test
    public void transfer() {
        Date date = DateUtils.getDate(2014, 1, 1);
        BigDecimal amount = new BigDecimal("100.00");

        CustomerImpl underTest = new CustomerImpl("1", "name", null);

        AccountImpl account = Mockito.mock(AccountImpl.class);
        Mockito.when(account.getInterestEarned(date)).thenReturn(new BigDecimal("1.00"));
        underTest.addAccount(account);

        AccountImpl account2 = Mockito.mock(AccountImpl.class);
        Mockito.when(account2.getInterestEarned(date)).thenReturn(new BigDecimal("2.50"));
        underTest.addAccount(account2);

        underTest.transfer(account, account2, amount, date);

        Mockito.verify(account).withdraw(amount, date);
        Mockito.verify(account2).deposit(amount, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void transfer_whenToAndFromAccountAreSame() {
        Date date = DateUtils.getDate(2014, 1, 1);
        BigDecimal amount = new BigDecimal("100.00");

        CustomerImpl underTest = new CustomerImpl("1", "name", null);

        AccountImpl account = Mockito.mock(AccountImpl.class);
        underTest.addAccount(account);

        underTest.transfer(account, account, amount, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void transfer_whenToAccountIsNotCustomersOwn() {
        Date date = DateUtils.getDate(2014, 1, 1);
        BigDecimal amount = new BigDecimal("100.00");

        CustomerImpl underTest = new CustomerImpl("1", "name", null);

        AccountImpl account = Mockito.mock(AccountImpl.class);
        AccountImpl account2 = Mockito.mock(AccountImpl.class);

        underTest.addAccount(account);

        underTest.transfer(account, account2, amount, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void transfer_whenFromAccountIsNotCustomersOwn() {
        Date date = DateUtils.getDate(2014, 1, 1);
        BigDecimal amount = new BigDecimal("100.00");

        CustomerImpl underTest = new CustomerImpl("1", "name", null);

        AccountImpl account = Mockito.mock(AccountImpl.class);
        AccountImpl account2 = Mockito.mock(AccountImpl.class);

        underTest.addAccount(account2);

        underTest.transfer(account, account2, amount, date);
    }
}