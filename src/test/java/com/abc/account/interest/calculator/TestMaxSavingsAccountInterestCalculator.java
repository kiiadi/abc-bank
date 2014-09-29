package com.abc.account.interest.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import mockit.Deencapsulation;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.IAccount;
import com.abc.account.MaxSavingsAccount;
import com.abc.exception.ValidationException;
import com.abc.transaction.Deposit;
import com.abc.transaction.Withdraw;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestMaxSavingsAccountInterestCalculator {

	private IInterestCalculator maxSavingsAccountInterestCalculator;
	
	private IAccount maxSavingAccount;
	
	@Before
	public void setUp() throws ValidationException, ParseException{
		maxSavingsAccountInterestCalculator = new MaxSavingsAccountInterestCalculator(0.1, 5);
		final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		maxSavingAccount = new MaxSavingsAccount("MS1");
		Deencapsulation.setField(maxSavingAccount, "openingDate", format.parse("01/01/2014"));
		final Deposit deposit = new Deposit(10000.00);
		maxSavingAccount.process(deposit);
		final Withdraw withdraw1 = new Withdraw(10.00);
		maxSavingAccount.process(withdraw1);
		final Withdraw withdraw2 = new Withdraw(11.00);
		maxSavingAccount.process(withdraw2);
	}
	
	@Test
	public void shouldNotBeEligibleForMaxSavingsRate(){
		final boolean isEligibleFroMaxSabings = Deencapsulation.invoke(maxSavingsAccountInterestCalculator, 
				"isEligibleForMaxSavingsRate", new Object [] {maxSavingAccount});
		assertFalse(isEligibleFroMaxSabings);
	}
	
	@Test 
	public void shouldBeEligibleForMaxSavingsRate() throws ValidationException, ParseException{
		maxSavingAccount = new MaxSavingsAccount("MS1");
		final Deposit deposit = new Deposit(100.00);
		maxSavingAccount.process(deposit);
		final Withdraw withdraw3 = new Withdraw(11.00);
		final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Deencapsulation.setField(withdraw3, "date", format.parse("09/17/2014"));
		maxSavingAccount.process(withdraw3);
		final boolean isEligibleFroMaxSabings = Deencapsulation.invoke(maxSavingsAccountInterestCalculator, 
				"isEligibleForMaxSavingsRate", new Object [] {maxSavingAccount});
		assertTrue(isEligibleFroMaxSabings);
	}
	
	@Test
	public void shouldCalculateInterestWithDefaultRate(){
		final double interest = maxSavingsAccountInterestCalculator.calculate(maxSavingAccount);
		assertEquals(7.38, interest, 0);
	}
	
	@Test
	public void shouldCalculateInterestWithMaxSavingsRate() throws ParseException{
		final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		maxSavingAccount = new MaxSavingsAccount("MS1");
		final Deposit deposit = new Deposit(1000.00);
		maxSavingAccount.process(deposit);
		Deencapsulation.setField(maxSavingAccount, "openingDate", format.parse("01/01/2014"));
		final double interest = maxSavingsAccountInterestCalculator.calculate(maxSavingAccount);
		assertEquals(36.99, interest, 0);
	}
	
}
