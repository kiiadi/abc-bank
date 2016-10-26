package com.abc;

import static java.util.Calendar.DAY_OF_YEAR;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class CustomerTest {
    private static final double DOUBLE_DELTA = 1e-15;

	@Test
	public void testGetStatement() {
		Account checkingAccount = new AccountChecking();
		Account savingsAccount = new AccountSavings();
		Account mSavingsAccount = new AccountMaxiSavings();
		Account sSavingsAccount = new AccountSuperSavings();
		
		Customer henry = new Customer("Henry")
				.openAccount(checkingAccount)
				.openAccount(savingsAccount)
				.openAccount(mSavingsAccount)
				.openAccount(sSavingsAccount);

		checkingAccount.deposit(100.0);
		savingsAccount.deposit(4000.0);
		savingsAccount.withdraw(200.0);
		mSavingsAccount.deposit(5000.0);
		mSavingsAccount.withdraw(300.0);
		sSavingsAccount.deposit(6000.0);
		sSavingsAccount.withdraw(400.0);

		assertEquals("Statement for " + henry.getName() + "\n\n" + "Checking Account\n" + "  deposit $100.00\n" + "Total $100.00\n"
				+ "\n" + "Savings Account\n" + "  deposit $4,000.00\n" + "  withdrawal $200.00\n" + "Total $3,800.00\n"
				+ "\n" + "Maxi Savings Account\n" + "  deposit $5,000.00\n" + "  withdrawal $300.00\n" + "Total $4,700.00\n"
				+ "\n" + "Super Savings Account\n" + "  deposit $6,000.00\n" + "  withdrawal $400.00\n" + "Total $5,600.00\n"
				+ "\n" + "Total In All Accounts $14,200.00", henry.getStatement());
	}

	@Test
	public void testNumberOfAccounts() {
		Customer oscar = new Customer("Oscar").openAccount(new AccountSavings());
		oscar.openAccount(new AccountChecking());
		oscar.openAccount(new AccountMaxiSavings());
		oscar.openAccount(new AccountSuperSavings());
		assertEquals(4, oscar.getNumberOfAccounts());
	}
	
	@Test
	public void testTotalInterestEarnedIn10Days() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final class DateProvider1 extends DateProvider {
			public Date now() {
				Calendar cal = Calendar.getInstance();
				cal.set(DAY_OF_YEAR, 2);
				return cal.getTime();
			}
		}
		final class DateProvider2 extends DateProvider {
			public Date now() {
				Calendar cal = Calendar.getInstance();
				cal.set(DAY_OF_YEAR, 12);
				return cal.getTime();
			}
		}
		Field field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider1());
		
		Account checkingAccount = new AccountChecking();
		Account savingsAccount = new AccountSavings();
		Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);
		checkingAccount.deposit(100.0);
		savingsAccount.deposit(4000.0);
		
		field = DateProvider.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, new DateProvider2());

        assertEquals("Interest calculation error.", 0.19452054794520546, henry.totalInterestEarned(), DOUBLE_DELTA);
        field.set(null, null);
	}
	
	@Test
	public void testTotalInterestEarnedZeroDays() {
		Account checkingAccount = new AccountChecking();
		Account savingsAccount = new AccountSavings();
		Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);
		checkingAccount.deposit(100.0);
		savingsAccount.deposit(4000.0);
		assertEquals("Interest calculation error.", 0.0, henry.totalInterestEarned(), DOUBLE_DELTA);
	}
	
	@Test
	public void testTransfer() {
		Account checkingAccount = new AccountChecking();
		Account savingsAccount = new AccountSavings();
		Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);
		checkingAccount.deposit(100.0);
		savingsAccount.deposit(4000.0);
		henry.transfer(100.0, savingsAccount, checkingAccount);
		assertEquals("Transfer error", 200.0, checkingAccount.sumTransactions(), DOUBLE_DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTransferSameAccounts() {
		Account checkingAccount = new AccountChecking();
		Customer henry = new Customer("Henry").openAccount(checkingAccount);
		checkingAccount.deposit(100.0);
		henry.transfer(100.0, checkingAccount, checkingAccount);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTransferNotMyToAccount() {
		Account checkingAccount = new AccountChecking();
		Account savingsAccount = new AccountSavings();
		checkingAccount.deposit(100.0);
		Customer henry = new Customer("Henry").openAccount(checkingAccount);
		henry.transfer(100.0, checkingAccount, savingsAccount);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTransferNotMyFromAccount() {
		Account checkingAccount = new AccountChecking();
		Account savingsAccount = new AccountSavings();
		checkingAccount.deposit(100.0);
		Customer henry = new Customer("Henry").openAccount(savingsAccount);
		henry.transfer(100.0, checkingAccount, savingsAccount);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTransferNoneMyAccount() {
		Account checkingAccount = new AccountChecking();
		Account savingsAccount = new AccountSavings();
		checkingAccount.deposit(100.0);
		Customer henry = new Customer("Henry");
		henry.transfer(100.0, checkingAccount, savingsAccount);
	}	
}
