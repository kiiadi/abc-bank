package com.abc.util;

import org.junit.Assert;
import org.junit.Test;

public class TestBankUtils {

	@Test
	public void testToDollars() {
		String s = BankUtils.toDollars(45);
		Assert.assertEquals("$45.00", s);
	}
	
	@Test
	public void testFormat() {
		String s1 = BankUtils.format(1, "account");
		Assert.assertEquals("1 account", s1);
		
		String s2 = BankUtils.format(2, "account");
		Assert.assertEquals("2 accounts", s2);
	}
}
