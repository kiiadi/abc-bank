package com.abc.transaction;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import com.abc.util.DateProvider;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestDeposit {
    
	private Deposit deposit;
	
	@Before
	public void setUp(){
		deposit = new Deposit(10.99);
	}
	
	@Test
	public void shouldGetDepositedAmount(){
		assertEquals(10.99, deposit.getAmount(), 0.0);
	}
	
	@Test
	public void shouldGetDepositedDate(){
		final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		assertEquals(dateFormat.format(DateProvider.getInstance().now()), dateFormat.format(deposit.getDate()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldGetIllegalArgumentException(){
		new Deposit(-1);
	}
}
