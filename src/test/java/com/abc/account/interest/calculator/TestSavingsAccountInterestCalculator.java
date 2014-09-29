package com.abc.account.interest.calculator;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import mockit.Deencapsulation;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.IAccount;
import com.abc.account.SavingsAccount;
import com.abc.transaction.Deposit;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestSavingsAccountInterestCalculator {
	
	private IInterestCalculator savingsAccountInterestCalculator;
	
	private IAccount savingsAccount;
	
	@Before
	public void setUp() throws ParseException{
		final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		savingsAccountInterestCalculator = new SavingsAccountInterestCalculator(0.1, 0.2);
		this.savingsAccount = new SavingsAccount(); 
		final Deposit deposit = new Deposit(1000.00);
		savingsAccount.process(deposit);
		Deencapsulation.setField(savingsAccount, "openingDate", format.parse("01/01/2014"));
	}
	
	@Test
	public void shouldCalculateInterestForBalance1000(){
		final double interest = savingsAccountInterestCalculator.calculate(savingsAccount);
		assertEquals(.74, interest, 0);
	} 
	
	@Test
	public void shouldCalculateInterestForBalance10000(){
		final Deposit deposit = new Deposit(9000.00);
		savingsAccount.process(deposit);
		final double interest = savingsAccountInterestCalculator.calculate(savingsAccount);
		assertEquals((13.32 + .74), interest, 0);
	} 

}
