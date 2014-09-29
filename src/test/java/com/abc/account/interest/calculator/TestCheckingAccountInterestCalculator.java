package com.abc.account.interest.calculator;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import mockit.Deencapsulation;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.CheckingAccount;
import com.abc.account.IAccount;
import com.abc.transaction.Deposit;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestCheckingAccountInterestCalculator {

	private IInterestCalculator checkingAccountInterestCalculator;
	
	private IAccount checkingAccount;
	
	@Before
	public void setUp() throws ParseException{
		this.checkingAccountInterestCalculator = new CheckingAccountInterestCalculator(0.1);
		final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		this.checkingAccount = new CheckingAccount(); 
		final Deposit deposit = new Deposit(10000.00);
		checkingAccount.process(deposit);
		
		Deencapsulation.setField(checkingAccount, "openingDate", format.parse("01/01/2014"));
	}
	
	@Test
	public void shouldCalculateInterest(){
		final double interest = checkingAccountInterestCalculator.calculate(checkingAccount);
		assertEquals(7.4, interest, 0);
	}
}
