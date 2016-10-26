package com.abc;

import static java.util.Calendar.DAY_OF_YEAR;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Test;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

	private final class DateProvider1 extends DateProvider {
		public Date now() {
			Calendar cal = Calendar.getInstance();
			cal.set(DAY_OF_YEAR, 1);
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

	@After
	public void tearDown() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, null);
    }

	@Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new AccountChecking());
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

	@Test
    public void customerSummary2Accounts() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new AccountChecking());
        john.openAccount(new AccountSavings());
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (2 accounts)", bank.customerSummary());
    }

	@Test
    public void customerSummary2Customers2Accounts() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        Customer jack = new Customer("Jack");
        john.openAccount(new AccountChecking());
        jack.openAccount(new AccountSavings());
        bank.addCustomer(john);
        bank.addCustomer(jack);

        assertEquals("Customer Summary\n - John (1 account)\n - Jack (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider1());

        Bank bank = new Bank();
        Account checkingAccount = new AccountChecking();
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

		field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider2());

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Field field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider1());

        Bank bank = new Bank();
        Account account = new AccountSavings();
        bank.addCustomer(new Customer("Bill").openAccount(account));

        account.deposit(1500.0);

		field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider2());

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider1());

        Bank bank = new Bank();
        Account account = new AccountMaxiSavings();
        bank.addCustomer(new Customer("Bill").openAccount(account));

        account.deposit(3000.0);

		field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider2());

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void super_savings_account() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider1());

        Bank bank = new Bank();
        Account account = new AccountSuperSavings();
        bank.addCustomer(new Customer("Bill").openAccount(account));

        account.deposit(3000.0);

		field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider2());

        assertEquals(150.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void multipleCustomersTotalInterest() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider1());

        Bank bank = new Bank();
        Account account1 = new AccountSuperSavings();
        Account account2 = new AccountMaxiSavings();
        bank.addCustomer(new Customer("Bill").openAccount(account1));
        bank.addCustomer(new Customer("Jack").openAccount(account2));

        account1.deposit(3000.0);
        account2.deposit(3000.0);

		field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider2());

        assertEquals(320.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
