package com.abc.account;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.abc.exception.NotEnoughBalanceException;
import com.abc.exception.ValidationException;
import com.abc.transaction.Deposit;
import com.abc.transaction.ITransaction;
import com.abc.transaction.Withdraw;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestMaxSavingsAccount {

	private IAccount maxSavingAccount;
	
	@Before
	public void setUp(){
		this.maxSavingAccount = new MaxSavingsAccount("MS1");
	}
	
	@Test
	public void shouldGetAccountNumber(){
		assertEquals("MS1", maxSavingAccount.getNumber());
	}
	
	@Test
	public void shouldInitializeTheAccountWithZeroBalance(){
		assertEquals(0.0, maxSavingAccount.getBalance(), 0);
	}

	@Test
	public void shouldDeposit100(){
		final Deposit deposit = new Deposit(100);
		maxSavingAccount.process(deposit);
		assertEquals(100.00, maxSavingAccount.getBalance(), 0);
	}
	
	@Test
	public void shouldWitdraw50() throws ValidationException{
		final Deposit deposit = new Deposit(100);
		maxSavingAccount.process(deposit);
		assertEquals(100.00, maxSavingAccount.getBalance(), 0);
		final Withdraw withdraw = new Withdraw(50);
		maxSavingAccount.process(withdraw);
		assertEquals(50.00, maxSavingAccount.getBalance(), 0);
	}
	
	
	@Test(expected = NotEnoughBalanceException.class)
	public void shouldGetNotEnoughBalanceException() throws ValidationException{
		final Deposit deposit = new Deposit(100);
		maxSavingAccount.process(deposit);
		assertEquals(100.00, maxSavingAccount.getBalance(), 0);
		final Withdraw withdraw = new Withdraw(101);
		maxSavingAccount.process(withdraw);
	}
	
	@Test
	public void shouldGetTransactions() throws ValidationException{
		final Deposit deposit = new Deposit(100);
		maxSavingAccount.process(deposit);
		List<ITransaction> transactions = maxSavingAccount.getTransactions();
		assertEquals(1, transactions.size());
		final Withdraw withdraw = new Withdraw(50);
		maxSavingAccount.process(withdraw);
		transactions = maxSavingAccount.getTransactions();
		assertEquals(2, transactions.size());
	}
}
