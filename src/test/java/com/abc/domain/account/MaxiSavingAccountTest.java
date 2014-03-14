package com.abc.domain.account;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MaxiSavingAccountTest {

	@Test
	public void testEquality() throws Exception {
		MaxiSavingsAccount one = new MaxiSavingsAccount();
		MaxiSavingsAccount another = new MaxiSavingsAccount();
		
		assertThat(one, is(another));
	}
}
