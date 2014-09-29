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
public class TestSavingsAccount {
	
	private IAccount savingAccount;
	
	@Before
	public void setUp(){
		savingAccount = new SavingsAccount();
	}
	
	@Test
	public void shouldInitializeTheAccountWithZeroBalance(){
		assertEquals(0.0, savingAccount.getBalance(), 0);
	}
	
	@Test
	public void shouldDeposit100(){
		final Deposit deposit = new Deposit(100.00);
		savingAccount.process(deposit);
		assertEquals(100.00, savingAccount.getBalance(), 0);
	}
	
	@Test
	public void shoudlWithDraw50() throws ValidationException{
		final Deposit deposit = new Deposit(100.00);
		savingAccount.process(deposit);
		assertEquals(100.00, savingAccount.getBalance(), 0);
		final Withdraw withdraw = new Withdraw(50.00);
		savingAccount.process(withdraw);
		assertEquals(50.00, savingAccount.getBalance(), 0);
	}
	
	
	@Test(expected = NotEnoughBalanceException.class)
	public void shouldGetNotEnoughBalanceException() throws ValidationException{
		final Deposit deposit = new Deposit(100);
		savingAccount.process(deposit);
		assertEquals(100.00, savingAccount.getBalance(), 0);
		final Withdraw withdraw = new Withdraw(101);
		savingAccount.process(withdraw);
	}
	

	@Test
	public void shouldGetTransactions() throws ValidationException{
		final Deposit deposit = new Deposit(100);
		savingAccount.process(deposit);
		List<ITransaction> transactions = savingAccount.getTransactions();
		assertEquals(1, transactions.size());
		final Withdraw withdraw = new Withdraw(50);
		savingAccount.process(withdraw);
		transactions = savingAccount.getTransactions();
		assertEquals(2, transactions.size());
	}

}
