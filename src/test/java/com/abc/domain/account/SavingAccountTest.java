package com.abc.domain.account;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SavingAccountTest {

	@Test
	public void testInterest() {
        SavingsAccount one = new SavingsAccount();
        SavingsAccount another = new SavingsAccount();
        
        assertThat(one, is(another));
	}
}
