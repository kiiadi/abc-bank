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
public class TestCheckingAccount {
	
	private IAccount checkingAccount;
	
	@Before
	public void setUp(){
		checkingAccount = new CheckingAccount();
	}
	
	@Test
	public void shouldInitializeTheAccountWithZeroBalance(){
		assertEquals(0.0, checkingAccount.getBalance(), 0);
	}

	@Test
	public void shouldDeposit100(){
		final Deposit deposit = new Deposit(100);
		checkingAccount.process(deposit);
		assertEquals(100.00, checkingAccount.getBalance(), 0);
	}
	
	@Test
	public void shouldWitdraw50() throws ValidationException{
		final Deposit deposit = new Deposit(100);
		checkingAccount.process(deposit);
		assertEquals(100.00, checkingAccount.getBalance(), 0);
		final Withdraw withdraw = new Withdraw(50);
		checkingAccount.process(withdraw);
		assertEquals(50.00, checkingAccount.getBalance(), 0);
	}
	
	
	@Test(expected = NotEnoughBalanceException.class)
	public void shouldGetNotEnoughBalanceException() throws ValidationException{
		final Deposit deposit = new Deposit(100);
		checkingAccount.process(deposit);
		assertEquals(100.00, checkingAccount.getBalance(), 0);
		final Withdraw withdraw = new Withdraw(101);
		checkingAccount.process(withdraw);
	}
	
	@Test
	public void shouldGetTransactions() throws ValidationException{
		final Deposit deposit = new Deposit(100);
		checkingAccount.process(deposit);
		List<ITransaction> transactions = checkingAccount.getTransactions();
		assertEquals(1, transactions.size());
		final Withdraw withdraw = new Withdraw(50);
		checkingAccount.process(withdraw);
		transactions = checkingAccount.getTransactions();
		assertEquals(2, transactions.size());
	}
}
