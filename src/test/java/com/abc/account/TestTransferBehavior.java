package com.abc.account;

import static org.junit.Assert.assertEquals;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Verifications;

import org.junit.Before;
import org.junit.Test;

import com.abc.exception.NotEnoughBalanceException;
import com.abc.exception.TransferFailedException;
import com.abc.exception.ValidationException;
import com.abc.transaction.Deposit;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestTransferBehavior {
	
	private IAccount checkingAccount;
	
	private IAccount savingAccount;
	
	@Injectable
	private IAccount mockedSavingAccount;
	
	@Before
	public void setUp(){
		checkingAccount = new CheckingAccount();
		savingAccount = new SavingsAccount();
		final Deposit deposit = new Deposit(100.00);
		checkingAccount.process(deposit);
	}
	
	@Test
	public void shouldTransferFromCheckingToSaving() throws ValidationException{
		checkingAccount.transfer(savingAccount, 60.00);
		assertEquals(40.00, checkingAccount.getBalance(), 0);
		assertEquals(60.00, savingAccount.getBalance(), 0);
	}

	@Test(expected = NotEnoughBalanceException.class)
	public void shouldGetNotEnoughBalanceException() throws ValidationException{
		checkingAccount.transfer(savingAccount, 101.00);
	}
	
	@Test(expected = TransferFailedException.class)
	public void shouldGetTransferFailedException() throws ValidationException{
		new NonStrictExpectations() {{
			mockedSavingAccount.process((Deposit) any);
			result = new Exception();
		}};
		
		checkingAccount.transfer(mockedSavingAccount, 60.00);
	}
	
	@Test
	public void shouldReturnTheMoneyToSourceAccount(){
		new NonStrictExpectations() {{
			mockedSavingAccount.process((Deposit) any);
			result = new Exception();
		}};
		
		try {
			checkingAccount.transfer(mockedSavingAccount, 60.00);
		} catch (ValidationException e) {
			final String message = e.getMessage();
			assertEquals("Transfer failed due to unexpected reasons, please contact the customer support.", message);
		}
		
		assertEquals(100.00, checkingAccount.getBalance(), 0);
		assertEquals(0.00, savingAccount.getBalance(), 0);
		
		new Verifications(){{
			Deposit deposit;
			mockedSavingAccount.process(deposit = withCapture());
			assertEquals(60.00, deposit.getAmount(), 0);
		}};
	}
}
