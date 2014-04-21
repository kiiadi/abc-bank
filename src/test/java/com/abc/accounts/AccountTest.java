package com.abc.accounts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.abc.exceptions.InsufficientFundsException;
import com.abc.exceptions.InvalidTransactionAmountException;

/**
 * Test-cases follow the following method-name pattern:
 * methodName_Scenario_ExpectedResult()
 *
 */
public class AccountTest {
	private BaseAccount account;
	
	@Before
	public void setUp() throws Exception {
		account = new BaseAccount(AccountType.CHECKING_ACCOUNT);
	}

	@Test
	public void getAccountId_ForNewAccount_RandomUUIDIsGenerated() {
		assertNotNull(account.getAccountId());
		assertTrue(account.getAccountId() == 1);
	}
	
	@Test
	public void getAccountType_ForNewAccount_AccountTypeIsSet() {
		assertNotNull(account.getAccountType());
		assertEquals(account.getAccountType(), AccountType.CHECKING_ACCOUNT);
	}

	@Test
	public void getTransactions_ForNewAccount_TransactionListIsInitialized() {
		assertNotNull(account.getTransactions());
		assertTrue(account.getTransactions().size() == 0);
	}
	
	@Test(expected=InvalidTransactionAmountException.class)
	public void deposit_NegativeAmountIsPassed_InvalidTransactionAmountExceptionIsThrown()
			throws InvalidTransactionAmountException {
		
		account.deposit(-100.0);
	}
	
	@Test
	public void deposit_PositiveAmountIsPassed_DepositIsAccepted()
			throws InvalidTransactionAmountException {
		
		account.deposit(100);
		
		assertTrue(account.getTransactions().size() == 1);
		assertEquals(account.getTransactions().get(0).getAmount(), 100, 0);
		
		account.getTransactions().clear();
	}
	
	@Test(expected=InvalidTransactionAmountException.class)
	public void withdraw_NegativeAmountIsPassed_InvalidTransactionAmountExceptionIsThrown()
			throws InvalidTransactionAmountException, InsufficientFundsException {
		
		account.withdraw(-100.0);
	}
	
	@Test(expected=InsufficientFundsException.class)
	public void withdraw_AmountGreaterThanBalanceIsPassed_InsufficientFundsExceptionIsThrown()
			throws InvalidTransactionAmountException, InsufficientFundsException {
		
		account.withdraw(100.0);
		
		account.getTransactions().clear();
	}
	
	@Test
	public void withdraw_BalanceIsEqualToAmountPassed_WithdrawalIsAccepted()
			throws InvalidTransactionAmountException, InsufficientFundsException {
		
		account.deposit(100.0);
		account.withdraw(100.0);
		
		assertTrue(account.getTransactions().size() == 2);
		assertEquals(account.getTransactions().get(0).getAmount(), 100, 0);
		assertEquals(account.getTransactions().get(1).getAmount(), -100, 0);
		
		account.getTransactions().clear();
	}
	
	@Test
	public void withdraw_BalanceIsGreaterThanAmountPassed_WithdrawalIsAccepted()
			throws InvalidTransactionAmountException, InsufficientFundsException {
		
		account.deposit(500.0);
		account.withdraw(100.0);
		
		assertTrue(account.getTransactions().size() == 2);
		assertEquals(account.getTransactions().get(0).getAmount(), 500, 0);
		assertEquals(account.getTransactions().get(1).getAmount(), -100, 0);
		
		account.getTransactions().clear();
	}
	
	@Test
	public void getBalance_DepositAndWithdrawalsMade_BalanceIsCalculated() throws Exception {
		account.deposit(500.0);
		account.withdraw(200.0);
		account.deposit(500.0);
		
		assertEquals(account.getBalance(), 800.0, 0);
		
		account.getTransactions().clear();
	}
	
}
