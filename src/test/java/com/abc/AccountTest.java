package com.abc;

import static java.util.Calendar.DAY_OF_YEAR;
import static java.util.Calendar.HOUR;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Test;

public class AccountTest {
    private static final double DOUBLE_DELTA = 1e-15;

	private final class DateProvider1 extends DateProvider {
		public Date now() {
			Calendar cal = Calendar.getInstance();
			cal.set(DAY_OF_YEAR, 1);
			cal.set(HOUR, 23);
			return cal.getTime();
		}
	}
	private final class DateProvider2 extends DateProvider {
		public Date now() {
			Calendar cal = Calendar.getInstance();
			cal.set(DAY_OF_YEAR, 366);
			return cal.getTime();
		}
	}
	private final class DateProvider3 extends DateProvider {
		public Date now() {
			Calendar cal = Calendar.getInstance();
			cal.set(DAY_OF_YEAR, 11);
			return cal.getTime();
		}
	}
	private final class DateProvider4 extends DateProvider {
		public Date now() {
			Calendar cal = Calendar.getInstance();
			cal.set(DAY_OF_YEAR, 21);
			return cal.getTime();
		}
	}
	
	@After
	public void tearDown() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, null);
    }

	@Test
    public void accountInterestChecking() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider1());

        Account account = new AccountChecking();
        account.deposit(100.0);

		field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider2());

        assertEquals(0.1, account.interestEarned(), DOUBLE_DELTA);
    }
	
	@Test
    public void accountInterestSavings() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider1());

        Account account = new AccountSavings();
        account.deposit(100.0);

		field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider2());

        assertEquals(0.1, account.interestEarned(), DOUBLE_DELTA);
    }
	
	@Test
    public void accountInterestSavingsLargeAmount() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider1());

        Account account = new AccountSavings();
        account.deposit(1100.0);

		field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider2());

        assertEquals(1.2, account.interestEarned(), DOUBLE_DELTA);
    }
	
	@Test
    public void accountInterestMaxiSavings() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider1());

        Account account = new AccountMaxiSavings();
        account.deposit(100.0);

		field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider2());

        assertEquals(2.0, account.interestEarned(), DOUBLE_DELTA);
    }
	
	@Test
    public void accountInterestMaxiSavingsMidAmount() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider1());

        Account account = new AccountMaxiSavings();
        account.deposit(1100.0);

		field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider2());

        assertEquals(25.0, account.interestEarned(), DOUBLE_DELTA);
    }
	
	@Test
    public void accountInterestMaxiSavingsLargeAmount() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider1());

        Account account = new AccountMaxiSavings();
        account.deposit(2100.0);

		field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider2());

        assertEquals(80.0, account.interestEarned(), DOUBLE_DELTA);
    }
	
	@Test
    public void accountInterestSuperSavingsNoWithdrawals() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider1());

        Account account = new AccountSuperSavings();
        account.deposit(100.0);

		field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider2());

        assertEquals(5.0, account.interestEarned(), DOUBLE_DELTA);
    }

	@Test
    public void accountInterestSuperSavingsWithdrawalsWithin10Days() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider1());

        Account account = new AccountSuperSavings();
        account.deposit(200.0);
        account.withdraw(100.0);

		field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider3());

        assertEquals(1d/365, account.interestEarned(), DOUBLE_DELTA);
    }

	@Test
    public void accountInterestSuperSavingsWithdrawalMixed() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider1());

        Account account = new AccountSuperSavings();
        account.deposit(200.0);
        account.withdraw(100.0);

		field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider4());

        assertEquals(1d/365 + 1000d * 0.05 / 365, account.interestEarned(), DOUBLE_DELTA);
    }

	@Test
	public void statementHeading() {
		Account account = new AccountChecking();
		assertEquals("Checking Account", account.statementHeading());
		account = new AccountSavings();
		assertEquals("Savings Account", account.statementHeading());
		account = new AccountMaxiSavings();
		assertEquals("Maxi Savings Account", account.statementHeading());
		account = new AccountSuperSavings();
		assertEquals("Super Savings Account", account.statementHeading());
	}
}
