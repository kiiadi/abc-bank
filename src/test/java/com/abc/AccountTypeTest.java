package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AccountTypeTest {

	public AccountTypeTest() {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetDescription() {
		assertEquals("Checking Account", AccountType.CHECKING.getDescription());
		assertEquals("Savings Account", AccountType.SAVINGS.getDescription());
		assertEquals("Maxi Savings Account",
				AccountType.MAXI_SAVINGS.getDescription());
	}

	@Test
	public void testGetCode() {
		assertEquals(0, AccountType.CHECKING.getCode());
		assertEquals(1, AccountType.SAVINGS.getCode());
		assertEquals(2, AccountType.MAXI_SAVINGS.getCode());
	}

}
