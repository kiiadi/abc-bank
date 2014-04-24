package com.abc.account;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class CheckingAccountTest {
	
	private Account checkingaccount;

	@Before
	public void setUp() throws Exception {
		checkingaccount = new CheckingAccount();
	}

	@Test
	public void testInterestEarned() throws Exception {

		checkingaccount.deposit(new BigDecimal("1000.00"));

		assertEquals(
				new BigDecimal("1000.00").multiply(new BigDecimal(".001")),
				checkingaccount.interestEarned());

	}

}
