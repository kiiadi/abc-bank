package com.abc.account;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class SavingsAccountTest {

	private Account savingsAccount;

	@Before
	public void setUp() throws Exception {
		savingsAccount = new SavingsAccount();
	}

	@Test
	public void testInterestEarned() throws Exception {

		savingsAccount.deposit(new BigDecimal("1000.00"));

		assertEquals(
				new BigDecimal("1000.00").multiply(new BigDecimal("0.001")),
				savingsAccount.interestEarned());

	}

	@Test
	public void testInterestEarnedOver1K() throws Exception {

		savingsAccount.deposit(new BigDecimal("5000.00"));
		
		BigDecimal expected = new BigDecimal("1000.00").multiply(new BigDecimal("0.001"))
			.add( new BigDecimal("4000.00").multiply(new BigDecimal("0.002") ));
		
		assertEquals(expected,
				savingsAccount.interestEarned());

	}
}
