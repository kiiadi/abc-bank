package com.abc;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: suppi
 * Date: 6/25/14
 * Time: 4:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class AccountTest {

    @Test
     public void testDeposits() {
        Account account = new Account(AccountType.CHECKING);
        account.deposit(BigDecimal.valueOf(2.00));
        account.withdraw(BigDecimal.valueOf(1.00));
        account.deposit(BigDecimal.valueOf(4.00));

        assertEquals(BigDecimal.valueOf(5.00), account.getCurrentBalance());

    }

    @Test
    public void testOverdraw() {
        Account account = new Account(AccountType.CHECKING);
        account.deposit(BigDecimal.valueOf(2.00));
        account.withdraw(BigDecimal.valueOf(2.00));
        account.withdraw(BigDecimal.valueOf(3.00));

        assertEquals(BigDecimal.valueOf(-3.00), account.getCurrentBalance());

    }
}
