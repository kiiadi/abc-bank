package com.abc;

import static com.abc.Account.Types.MAXI_SAVINGS;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	private Account anyAccount, anyAccount2, from, to;
	private Account maxiSavings;
	private LocalDate mockNow = LocalDate.of(2015, Month.JANUARY, 8);
	
	@Before
	public void givenAccounts() {
		anyAccount = createAnyAccount();
		anyAccount2 = createAnyAccount();
		from = createAnyAccount();
		to = createAnyAccount();
		maxiSavings = MAXI_SAVINGS.newInstance();
		DateProvider.setInstance( new DateProvider() {
			@Override
			public LocalDate now() {
				return mockNow;
			}
		});
	}

	@After
	public void resetDateProvider() {
		DateProvider.setInstance(null);
	}
	
	private Account createAnyAccount() {
		return new Account(null) {
			@Override
			public double interestEarned() {
				throw new RuntimeException();
			}
		};
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void givenAnyAccount_whenDepositeNegativeAmount_thenRaiseException() {
		anyAccount.deposit(-10.0);
	}
	@Test(expected=IllegalArgumentException.class)
	public void givenAnyAccount_whenWithdrawNegativeAmount_thenRaiseException() {
		anyAccount.deposit(-10.0);
	}
	
	@Test
	public void givenAnyAccount_whenDeposite_thenAddTransactionWithPositiveAmount() {
		anyAccount.deposit(10.0);
		assertEquals(1, anyAccount.getTransactions().size());
		assertAmount(10.0, anyAccount.getTransactions().get(0).getAmount());
	}
	
	@Test
	public void givenAnyAccount_whenWithdraw_thenAddTransactionWithNegativeAmount() {
		anyAccount.withdraw(10.0);
		assertEquals(1, anyAccount.getTransactions().size());
		assertAmount(-10.0, anyAccount.getTransactions().get(0).getAmount());
	}

	@Test(expected=IllegalArgumentException.class)
	public void givenTwoAccountsOfDifferentCustomers_whenTransfer_thenRaiseException() {
		new Customer("John").openAccount(anyAccount);
		new Customer("Eric").openAccount(anyAccount2);
		Account from = anyAccount, to = anyAccount2;
		Account.customerTransfer(from, to, 10.0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void givenTwoAccountsOfSameCustomer_whenTransferNegativeAmount_thenRaiseException() {
		Customer customer = new Customer("John");
		customer.openAccount(from);
		customer.openAccount(to);
		Account.customerTransfer(from, to, -10.0);
	}
	
	@Test
	public void givenTwoAccountsOfSameCustomer_whenTransfer_assumingIdeallyWithoutHarssleLikeAtomicEtc_thenAddTwoTransactions() {
		Customer customer = new Customer("John");
		customer.openAccount(from);
		customer.openAccount(to);
		Account.customerTransfer(from, to, 10.0);
		
		assertAmount(-10.0, from.getTransactions().get(0).getAmount());
		assertAmount(10.0, to.getTransactions().get(0).getAmount());
	}

	
    @Test
    public void givenMaxiSavingsAccountsWithdrawTenDaysAgo_whenCalculateInterest_thenUseLowerRate() {
		prepareOldBalanceAndWithdraw(100.0, 10, 20.0 );
        assertAmount((100-20)*0.001, maxiSavings.interestEarned());
    }

    @Test
    public void givenMaxiSavingsAccountsWithdraw11DaysAgo_whenCalculateInterest_thenUseLowerRate() {
		prepareOldBalanceAndWithdraw(100.0, 11, 20.0 );
        assertAmount((100-20)*0.05, maxiSavings.interestEarned());
    }

	private void prepareOldBalanceAndWithdraw(double oldBalance, int withdrawDaysAgo, double withDrawAmount) {
		int oldBalanceDepositedDaysAgo = 99;
		LocalDate now = mockNow;
		mockNow = now.minusDays(oldBalanceDepositedDaysAgo);
		maxiSavings.deposit(oldBalance);
		mockNow = now.minusDays(withdrawDaysAgo);
    	maxiSavings.withdraw(withDrawAmount);
    	mockNow = now;
	}
	
	private void assertAmount(double expect, double actual) {
	    assertEquals(expect, actual, 1e-15);
	}
}
