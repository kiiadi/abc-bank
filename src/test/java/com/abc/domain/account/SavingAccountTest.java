package com.abc.domain.account;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.time.TimePoint;

public class SavingAccountTest {

	@Test
	public void testInterest() {
		SavingsAccount account = new SavingsAccount();
		account.deposit(Money.dollars(10), TimePoint.now());
		assertThat(account.interest(), is(Money.dollars(0.01)));
		
		account = new SavingsAccount();
		account.deposit(Money.dollars(10000), TimePoint.now());
		assertThat(account.interest(), is(Money.dollars(19.0)));
	}
}
