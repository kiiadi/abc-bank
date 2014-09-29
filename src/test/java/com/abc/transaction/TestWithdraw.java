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
public class TestWithdraw {
	
	private Withdraw withdraw;
	
	@Before
	public void setUp(){
		this.withdraw = new Withdraw(9.9);
	}

	@Test
	public void shouldGetWithdrawAmount(){
		assertEquals(9.9, withdraw.getAmount(), 0.0);
	}
	
	@Test
	public void shouldGetDepositedDate(){
		final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		assertEquals(dateFormat.format(DateProvider.getInstance().now()), dateFormat.format(withdraw.getDate()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldGetIllegalArgumentException(){
		new Withdraw(-1);
	}
}
