package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {

	Account robAccount;
	Account teslaAccount;
	Account charlieAccount;

	public AccountTest() {

	}

	@Before
	public void setUp() throws Exception {

		robAccount = new Account(AccountType.CHECKING);
		teslaAccount = new Account(AccountType.SAVINGS);
		charlieAccount = new Account(AccountType.MAXI_SAVINGS);

		robAccount.deposit(1000.0d);
		teslaAccount.deposit(2000.0d);
		teslaAccount.transfer(1000.0d, charlieAccount);
	}

	@Test
	public void testGetAccountType() {
		assertEquals(AccountType.CHECKING, robAccount.getAccountType());
		assertEquals(AccountType.SAVINGS, teslaAccount.getAccountType());
		assertEquals(AccountType.MAXI_SAVINGS, charlieAccount.getAccountType());
	}

	@Test
	public void testGetBalance() {
		Double expectedResult = 1000.0d;
		Double actualResult = robAccount.getBalance();
		assertEquals(expectedResult, actualResult);
		actualResult = teslaAccount.getBalance();
		assertEquals(expectedResult, actualResult);
		actualResult = charlieAccount.getBalance();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testDeposit() {

		Double expectedResult = 3000.0d;
		Double amount = 2000.0d;
		teslaAccount.deposit(amount);
		Double actualResult = teslaAccount.getBalance();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testDepositErrorCase_AmountZero() {

		Double amount = 0.0d;
		String expectedClassName = "java.lang.IllegalArgumentException";
		String expectedErrorMessage = "deposit valid amount";

		String actualClassName = "";
		String actualErrorMessage = "";

		try {
			teslaAccount.deposit(amount);
		} catch (Exception e) {
			actualClassName = e.getClass().getName();
			actualErrorMessage = e.getMessage();
		}

		assertEquals(expectedClassName, actualClassName);
		assertEquals(expectedErrorMessage, actualErrorMessage);
	}

	@Test
	public void testWithdraw() {
		Double expectedResult = 800.0d;
		Double amount = 200.0d;
		teslaAccount.withdraw(amount);
		Double actualResult = teslaAccount.getBalance();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testWithdrawErrorCase_AmountOverBalance() {

		Double amount = 1200.0d;
		String expectedClassName = "java.lang.IllegalArgumentException";
		String expectedErrorMessage = "Insufficient Funds";

		String actualClassName = "";
		String actualErrorMessage = "";

		try {
			teslaAccount.withdraw(amount);
		} catch (Exception e) {
			actualClassName = e.getClass().getName();
			actualErrorMessage = e.getMessage();
		}

		assertEquals(expectedClassName, actualClassName);
		assertEquals(expectedErrorMessage, actualErrorMessage);
	}

	@Test
	public void testTransfer() {

		Double expectedResultTesla = 500.0d;
		Double expectedResultRob = 1500.0d;

		Double amount = 500.0d;
		teslaAccount.transfer(amount, robAccount);
		Double actualResultTesla = teslaAccount.getBalance();
		Double actualResultRob = robAccount.getBalance();

		assertEquals(expectedResultTesla, actualResultTesla);
		assertEquals(expectedResultRob, actualResultRob);
	}

	@Test
	public void testTransferErrorCase_AmountOverBalance() {

		Double amount = 1200.0d;
		String expectedClassName = "java.lang.IllegalArgumentException";
		String expectedErrorMessage = "Insufficient Funds";

		String actualClassName = "";
		String actualErrorMessage = "";

		try {
			teslaAccount.transfer(amount, robAccount);
		} catch (Exception e) {
			actualClassName = e.getClass().getName();
			actualErrorMessage = e.getMessage();
		}

		assertEquals(expectedClassName, actualClassName);
		assertEquals(expectedErrorMessage, actualErrorMessage);
	}

	@Test
	public void testTransferErrorCase_AmountZero() {

		Double amount = 0.0d;
		String expectedClassName = "java.lang.IllegalArgumentException";

		String actualClassName = "";

		try {
			teslaAccount.transfer(amount, robAccount);
		} catch (Exception e) {
			actualClassName = e.getClass().getName();
		}

		assertEquals(expectedClassName, actualClassName);
	}

	@Test
	public void testInterestEarnedChecking_BalanceOver1000() {
		Account firstAccount = new Account(AccountType.CHECKING);
		Double amount = 2000.0d;
		Double expectedResult = 2.0d;
		firstAccount.deposit(amount);
		Double actualResult = firstAccount.interestEarned();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testInterestEarnedChecking_BalanceExactly1000() {
		Account firstAccount = new Account(AccountType.CHECKING);
		Double amount = 1000.0d;
		Double expectedResult = 1.0d;
		firstAccount.deposit(amount);
		Double actualResult = firstAccount.interestEarned();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testInterestEarnedChecking_BalanceLessThan1000() {
		Account firstAccount = new Account(AccountType.CHECKING);
		Double amount = 100.0d;
		Double expectedResult = 0.1d;
		firstAccount.deposit(amount);
		Double actualResult = firstAccount.interestEarned();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testInterestEarnedChecking_BalanceLessThanZero() {
		Account firstAccount = new Account(AccountType.CHECKING);
		Double amount = -2000.0d;
		String expectedResult = "deposit valid amount";
		String actualResult = "";
		try {
			firstAccount.deposit(amount);
		} catch (Exception e) {
			actualResult = e.getMessage();
		}

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testInterestEarnedSavings_BalanceOver1000() {
		Account firstAccount = new Account(AccountType.SAVINGS);
		Double amount = 2000.0d;
		Double expectedResult = 3.0d;
		firstAccount.deposit(amount);
		Double actualResult = firstAccount.interestEarned();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testInterestEarnedSavings_BalanceExactly1000() {
		Account firstAccount = new Account(AccountType.SAVINGS);
		Double amount = 1000.0d;
		Double expectedResult = 1.0d;
		firstAccount.deposit(amount);
		Double actualResult = firstAccount.interestEarned();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testInterestEarnedSavings_BalanceUnder1000() {
		Account firstAccount = new Account(AccountType.SAVINGS);
		Double amount = 100.0d;
		Double expectedResult = 0.1d;
		firstAccount.deposit(amount);
		Double actualResult = firstAccount.interestEarned();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testInterestEarnedSavings_BalanceLesserThanZero() {
		Account firstAccount = new Account(AccountType.SAVINGS);
		Double amount = -2000.0d;
		String expectedResult = "deposit valid amount";
		String actualResult = "";
		try {
			firstAccount.deposit(amount);
		} catch (Exception e) {
			actualResult = e.getMessage();
		}

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testInterestEarnedMaxiSavings_BalanceUnder1000() {
		Double amount = 100.0d;
		Double expectedResult = 2.0d;
		Account firstAccount = new Account(AccountType.MAXI_SAVINGS);
		firstAccount.deposit(amount);
		Double actualResult = firstAccount.interestEarned();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testInterestEarnedMaxiSavings_BalanceExactly1000() {
		Double amount = 1000.0d;
		Double expectedResult = 20.0d;
		Account firstAccount = new Account(AccountType.MAXI_SAVINGS);
		firstAccount.deposit(amount);
		Double actualResult = firstAccount.interestEarned();

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testInterestEarnedMaxiSavings_BalanceExactly2000() {
		Double amount = 2000.0d;
		Double expectedResult = 70.0d;
		Account firstAccount = new Account(AccountType.MAXI_SAVINGS);
		firstAccount.deposit(amount);
		Double actualResult = firstAccount.interestEarned();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testInterestEarnedMaxiSavings_BalanceExactly3000() {
		Double amount = 3000.0d;
		Double expectedResult = 170.0d;
		Account firstAccount = new Account(AccountType.MAXI_SAVINGS);
		firstAccount.deposit(amount);
		Double actualResult = firstAccount.interestEarned();
		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void testInterestEarnedMaxiSavings_BalanceUnderZero_case1() {
		Double amount = -2000.0d;
		String expectedMessage = "deposit valid amount";
		String actualMessage = "";
		Account firstAccount = new Account(AccountType.MAXI_SAVINGS);
		try {
			firstAccount.deposit(amount);
		} catch (Exception e) {
			actualMessage = e.getMessage();
		}
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testInterestEarnedMaxiSavings_BalanceUnderZero_case2() {
		Double amount = -3000.0d;
		String expectedMessage = "deposit valid amount";
		String actualMessage = "";
		Account firstAccount = new Account(AccountType.MAXI_SAVINGS);
		try {
			firstAccount.deposit(amount);
		} catch (Exception e) {
			actualMessage = e.getMessage();
		}
		assertEquals(expectedMessage, actualMessage);

	}

	@Test
	public void testGetAllTransactions() {
		assertEquals(2, teslaAccount.getAllTransactions().size());
	}

}
