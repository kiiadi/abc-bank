package com.abc.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestNotEnoughBalanceException {
	
	private ValidationException notEnoughBalanceException;
	
	@Before
	public void setUp(){
		this.notEnoughBalanceException = new NotEnoughBalanceException(100.10, 110.90);
	}
	
	@Test
	public void shouldGetNotEnoughBalanceExceptionMessage(){
		assertEquals("Unfortunately account doesn't have enough balance to serve "
				+ "the requested withdrwal! Available balance: 100.1Requested amount: 110.9", 
				notEnoughBalanceException.getMessage());
	}

}
