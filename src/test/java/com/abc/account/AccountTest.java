package com.abc.account;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.abc.exception.InsufficientFundsException;
import com.abc.exception.InvalidTransactionException;
import com.abc.transaction.TransactionType;
import com.abc.util.Constants;
import com.abc.util.Utils;

public class AccountTest {

	@Test
	public void testCheckingAccountCreation() {
		Account account = new CheckingAccount();
		
		assertEquals(account.getAccountType(), AccountType.CHECKING.getDisplayName());
		assertEquals(account.getAccountBalance(), BigDecimal.ZERO);
		assertNotNull(account.getTransactions());
		assertEquals(account.getTransactions().size(), 0);
		assertEquals(Utils.normalizeDay(account.getOpeningDate()), Utils.normalizeDay(Utils.getNow()));
	}
	
	@Test
	public void testSavingsAccountCreation() {
		Account account = new SavingsAccount();
		assertEquals(account.getAccountType(), AccountType.SAVINGS.getDisplayName());
		assertEquals(account.getAccountBalance(), BigDecimal.ZERO);
		assertNotNull(account.getTransactions());
		assertEquals(account.getTransactions().size(), 0);
		assertEquals(Utils.normalizeDay(account.getOpeningDate()), Utils.normalizeDay(Utils.getNow()));
	}
	
	@Test
	public void testMaxSavingsAccountCreation() {
		Account account = new MaxSavingsAccount();
		assertEquals(account.getAccountType(), AccountType.MAX_SAVINGS.getDisplayName());
		assertEquals(account.getAccountBalance(), BigDecimal.ZERO);
		assertNotNull(account.getTransactions());
		assertEquals(account.getTransactions().size(), 0);
		assertEquals(Utils.normalizeDay(account.getOpeningDate()), Utils.normalizeDay(Utils.getNow()));
	}
	
	@Test
	public void testDeposit() throws InvalidTransactionException {
		Account account = new CheckingAccount();
		account.processTransaction(new BigDecimal(100D), TransactionType.DEPOSIT);
		
		assertEquals(account.getAccountBalance(), new BigDecimal(100D));
	}
	
	@Test
	public void testMultipleDeposits() throws InvalidTransactionException {
		Account account = new SavingsAccount();
		account.processTransaction(new BigDecimal(100D), TransactionType.DEPOSIT);
		account.processTransaction(new BigDecimal(150D), TransactionType.DEPOSIT);
		account.processTransaction(new BigDecimal(200D), TransactionType.DEPOSIT);
		
		assertEquals(account.getAccountBalance(), new BigDecimal(450D));
	}

	@Test
	public void testWithdrawal() throws InvalidTransactionException {
		Account account = new CheckingAccount();
		account.setAccountBalance(new BigDecimal(500D));
		account.processTransaction(new BigDecimal(100D), TransactionType.WITHDRAW);
		
		assertEquals(account.getAccountBalance(), new BigDecimal(400D));
	}
	
	@Test(expected=InvalidTransactionException.class)
	public void testTransferException() throws InvalidTransactionException {
		Account account1 = new CheckingAccount();
		Account account2 = new CheckingAccount();
		account1.processTransferTransaction(new BigDecimal(200D), TransactionType.DEPOSIT, account2);
	}

	@Test
	public void testTransfer() throws InvalidTransactionException {
		Account account1 = new CheckingAccount();
		account1.setAccountBalance(new BigDecimal(500D));
		
		Account account2 = new CheckingAccount();
		account2.setAccountBalance(new BigDecimal(100D));
		
		account1.processTransferTransaction(new BigDecimal(200D), TransactionType.TRANSFER, account2);
		
		assertEquals(account1.getAccountBalance(), new BigDecimal(300D));
		assertEquals(account2.getAccountBalance(), new BigDecimal(300D));
	}

	@Test(expected=InsufficientFundsException.class)
	public void testTransferWithdrawException() throws InvalidTransactionException {
		Account account1 = new CheckingAccount();
		account1.setAccountBalance(new BigDecimal(500D));
		
		Account account2 = new CheckingAccount();
		account2.setAccountBalance(new BigDecimal(100D));
		
		account1.processTransferTransaction(new BigDecimal(600D), TransactionType.TRANSFER, account2);
	}

	@Test
	public void testWhenWithdrawalErrorDepositDoesNotHappen() {
		Account account1 = new CheckingAccount();
		account1.setAccountBalance(new BigDecimal(500D));
		
		Account account2 = new CheckingAccount();
		account2.setAccountBalance(new BigDecimal(100D));
		
		try {
			account1.processTransferTransaction(new BigDecimal(600D), TransactionType.TRANSFER, account2);
		} catch (InvalidTransactionException e) {
			assertNotNull(e);
			assertTrue(e instanceof InsufficientFundsException);
		}
		
		assertEquals(account1.getAccountBalance(), new BigDecimal(500D));
		assertEquals(account2.getAccountBalance(), new BigDecimal(100D));
	}

	@Test (expected=InsufficientFundsException.class)
	public void testWithdrawalError() throws InvalidTransactionException {
		Account account = new CheckingAccount();
		account.processTransaction(new BigDecimal(100D), TransactionType.WITHDRAW);
	}
	
	@Test
	public void testWithdrawalErrorExceptionMsg() {
		Account account = new CheckingAccount();
		Exception expectedExcetion = null;
		try {
			account.processTransaction(new BigDecimal(100D), TransactionType.WITHDRAW);
		} catch (InvalidTransactionException e) {
			expectedExcetion = e;
		}
		assertEquals(expectedExcetion.getMessage(), Constants.WITHDRAWAL_EXCEEDS_ERROR);
	}
	
	@Test
	public void testCheckingAccountInterest() throws InvalidTransactionException, ParseException {
		Account account = new CheckingAccount();
		account.setOpeningDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"));
		account.processTransaction(new BigDecimal(10000D), TransactionType.DEPOSIT);
		account.setEndDateForInterestCalc(new SimpleDateFormat("yyyy/MM/dd").parse("2015/04/09"));
		
		assertEquals(new BigDecimal(account.getInterestEarned()).setScale(2, BigDecimal.ROUND_HALF_UP), 
					 new BigDecimal(0.27D).setScale(2, BigDecimal.ROUND_HALF_UP));
	}
	
	@Test
	public void testSavingsAccountInterest() throws InvalidTransactionException, ParseException {
		Account account = new SavingsAccount();
		account.setOpeningDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"));
		account.processTransaction(new BigDecimal(10000D), TransactionType.DEPOSIT);
		account.setEndDateForInterestCalc(new SimpleDateFormat("yyyy/MM/dd").parse("2015/04/09"));
		
		assertEquals(new BigDecimal(account.getInterestEarned()).setScale(2, BigDecimal.ROUND_HALF_UP), 
					 new BigDecimal(0.50D).setScale(2, BigDecimal.ROUND_HALF_UP));
	}
	
	@Test
	public void testMaxSavingsAccountInterestWithNoWithdrawal() throws InvalidTransactionException, ParseException {
		Account account = new MaxSavingsAccount();
		account.setOpeningDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"));
		account.processTransaction(new BigDecimal(10000D), TransactionType.DEPOSIT);
		account.setEndDateForInterestCalc(new SimpleDateFormat("yyyy/MM/dd").parse("2015/04/09"));
		
		assertEquals(new BigDecimal(account.getInterestEarned()).setScale(2, BigDecimal.ROUND_HALF_UP), 
					 new BigDecimal(132.88D).setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	@Test
	public void testMaxSavingsAccountInterestWithWithdrawalBeyond10Days() throws InvalidTransactionException, ParseException {
		Account account = new MaxSavingsAccount();
		account.setOpeningDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"));
		account.processTransaction(new BigDecimal(11000D), TransactionType.DEPOSIT);
		account.processTransaction(new BigDecimal(1000D), TransactionType.WITHDRAW);
		account.getTransactions().get(1).setTransDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/03/01"));
		account.setEndDateForInterestCalc(new SimpleDateFormat("yyyy/MM/dd").parse("2015/04/09"));
		
		assertEquals(new BigDecimal(account.getInterestEarned()).setScale(2, BigDecimal.ROUND_HALF_UP), 
					 new BigDecimal(0.27D).setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	@Test
	public void testMaxSavingsAccountInterestWithWithdrawalInLast10Days() throws InvalidTransactionException, ParseException {
		Account account = new MaxSavingsAccount();
		account.setOpeningDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"));
		account.processTransaction(new BigDecimal(11000D), TransactionType.DEPOSIT);
		account.processTransaction(new BigDecimal(1000D), TransactionType.WITHDRAW);
		account.setEndDateForInterestCalc(new SimpleDateFormat("yyyy/MM/dd").parse("2015/04/09"));
		
		assertEquals(new BigDecimal(account.getInterestEarned()).setScale(2, BigDecimal.ROUND_HALF_UP), 
					 new BigDecimal(0.27D).setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	@Test
	public void testGetDetailedStatementFor1Account() throws InvalidTransactionException, ParseException {
		Account account = new CheckingAccount("11");
		account.processTransaction(new BigDecimal(10000D), TransactionType.DEPOSIT);
		account.getTransactions().get(0).setTransDate(new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01"));
				
		String expectedOutout = 
				new StringBuilder().append("AccountNumber: 	11	AccountType: 	Checking")
				.append(Constants.LINE_SEPARATOR)
				.append("Transaction Log: ")
				.append(Constants.LINE_SEPARATOR)
				.append("2015-01-01	Deposit	$10,000.00")
				.append(Constants.LINE_SEPARATOR)
				.append("Total for Account: 	$10,000.00").toString();
				
		assertEquals(expectedOutout, account.getDetailedStatement());
	}
}
