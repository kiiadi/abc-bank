package com.abc;

import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link com.abc.AccountImpl}
 */
public class AccountImplTest extends TestCase {

    private static final Date SOME_DATE = DateUtils.getDate(2014, 5, 1);

    @Test
    public void testDeposit() throws Exception {
        AccountImpl underTest = createAccount();

        BigDecimal amount = BigDecimal.TEN;
        underTest.deposit(amount, SOME_DATE);

        assertEquals(amount, underTest.getBalance());

        Transaction expectedTxn = new Transaction(amount, SOME_DATE, amount);
        checkTxn(expectedTxn, underTest.getTransactions().get(0));
    }

    @Test
    public void testWithdraw() throws Exception {
        AccountImpl underTest = createAccount();

        BigDecimal amount = BigDecimal.TEN;
        underTest.withdraw(amount, SOME_DATE);

        assertEquals(amount.negate(), underTest.getBalance());

        Transaction expectedTxn = new Transaction(amount.negate(), SOME_DATE, amount.negate());
        checkTxn(expectedTxn, underTest.getTransactions().get(0));
    }

    @Test
    public void testDepositAndWithdraw() throws Exception {
        AccountImpl underTest = createAccount();

        Date date1 = DateUtils.getDate(2014, 5, 1);
        Date date2 = DateUtils.getDate(2014, 5, 3);

        BigDecimal depositAmount = BigDecimal.TEN;
        underTest.deposit(depositAmount, date1);

        BigDecimal withdrawalAmount = BigDecimal.ONE;
        underTest.withdraw(withdrawalAmount, date2);

        BigDecimal total = depositAmount.subtract(withdrawalAmount);
        assertEquals(total, underTest.getBalance());

        Transaction expectedDepositTxn = new Transaction(depositAmount, date1, depositAmount);
        checkTxn(expectedDepositTxn, underTest.getTransactions().get(0));
        Transaction expectedWithdrawTxn = new Transaction(withdrawalAmount.negate(), date2, total);
        checkTxn(expectedWithdrawTxn, underTest.getTransactions().get(1));
    }

    private void checkTxn(Transaction expected, Transaction actual) {
        assertEquals(expected.getAmount(), actual.getAmount());
        assertEquals(expected.getTransactionDate(), actual.getTransactionDate());
        assertEquals(expected.getResultingBalance(), actual.getResultingBalance());
    }

    @Test
    public void testGetInterestEarned() throws Exception {
        InterestCalculator interestCalculator = Mockito.mock(InterestCalculator.class);

        BigDecimal interest = BigDecimal.TEN;
        when(interestCalculator.getInterestAccrued(SOME_DATE)).thenReturn(interest);

        AccountImpl underTest = new AccountImpl("1", AccountType.CHECKING, interestCalculator, null);

        assertEquals(interest, underTest.getInterestEarned(SOME_DATE));
    }

    @Test
    public void testGetDaysSinceLastWithdrawal_WhenOneWithdrawal() throws Exception {
        AccountImpl underTest = createAccount();

        BigDecimal depositAmount = BigDecimal.TEN;
        underTest.deposit(depositAmount, DateUtils.getDate(2014, 5, 1));

        BigDecimal withdrawalAmount = BigDecimal.ONE;
        underTest.withdraw(withdrawalAmount, DateUtils.getDate(2014, 5, 3));

        assertEquals(null, underTest.getDaysSinceLastWithdrawal(DateUtils.getDate(2014, 5, 1)));
        assertEquals(null, underTest.getDaysSinceLastWithdrawal(DateUtils.getDate(2014, 5, 2)));
        assertEquals(0, (int)underTest.getDaysSinceLastWithdrawal(DateUtils.getDate(2014, 5, 3)));
        assertEquals(1, (int)underTest.getDaysSinceLastWithdrawal(DateUtils.getDate(2014, 5, 4)));
        assertEquals(30, (int)underTest.getDaysSinceLastWithdrawal(DateUtils.getDate(2014, 6, 3)));
    }

    @Test
    public void testGetDaysSinceLastWithdrawal_WhenTwoWithdrawals() throws Exception {
        AccountImpl underTest = createAccount();

        BigDecimal depositAmount = BigDecimal.TEN;
        underTest.deposit(depositAmount, DateUtils.getDate(2014, 5, 1));

        BigDecimal withdrawalAmount = BigDecimal.ONE;
        underTest.withdraw(withdrawalAmount, DateUtils.getDate(2014, 5, 3));

        BigDecimal withdrawalAmount2 = BigDecimal.ONE;
        underTest.withdraw(withdrawalAmount2, DateUtils.getDate(2014, 5, 5));

        assertEquals(null, underTest.getDaysSinceLastWithdrawal(DateUtils.getDate(2014, 5, 1)));
        assertEquals(null, underTest.getDaysSinceLastWithdrawal(DateUtils.getDate(2014, 5, 2)));
        assertEquals(0, (int)underTest.getDaysSinceLastWithdrawal(DateUtils.getDate(2014, 5, 3)));
        assertEquals(1, (int)underTest.getDaysSinceLastWithdrawal(DateUtils.getDate(2014, 5, 4)));
        assertEquals(0, (int)underTest.getDaysSinceLastWithdrawal(DateUtils.getDate(2014, 5, 5)));
        assertEquals(1, (int)underTest.getDaysSinceLastWithdrawal(DateUtils.getDate(2014, 5, 6)));
        assertEquals(30, (int)underTest.getDaysSinceLastWithdrawal(DateUtils.getDate(2014, 6, 5)));
    }

    @Test
    public void testGetDaysSinceLastWithdrawal_WhenNoWithdrawal() throws Exception {
        AccountImpl underTest = createAccount();

        Date date1 = DateUtils.getDate(2014, 5, 1);

        BigDecimal depositAmount = BigDecimal.TEN;
        underTest.deposit(depositAmount, date1);

        assertEquals(null, underTest.getDaysSinceLastWithdrawal(date1));
    }

    @Test
    public void testGetBalanceForDate() throws Exception {
        AccountImpl underTest = createAccount();

        Date date1 = DateUtils.getDate(2014, 5, 1);
        Date date2 = DateUtils.getDate(2014, 5, 3);

        BigDecimal depositAmount = new BigDecimal("10.00");
        underTest.deposit(depositAmount, date1);

        BigDecimal withdrawalAmount = new BigDecimal("1.00");
        underTest.withdraw(withdrawalAmount, date2);

        assertEquals(BigDecimal.ZERO, underTest.getBalance(new Date(0)));
        assertEquals(depositAmount, underTest.getBalance(date1));
        assertEquals(depositAmount, underTest.getBalance(DateUtils.addOneDay(date1)));
        assertEquals(new BigDecimal("9.00"), underTest.getBalance(date2));
        assertEquals(new BigDecimal("9.00"), underTest.getBalance(DateUtils.addOneDay(date2)));
    }

    @Test
    public void testGetType() throws Exception {
        AccountImpl underTest = createAccount();
        assertEquals(AccountType.CHECKING, underTest.getAccountType());
    }

    private AccountImpl createAccount() {
        return new AccountImpl("1", AccountType.CHECKING, null, null);
    }
}
