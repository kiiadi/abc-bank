package com.abc.domain.statement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.abc.domain.account.Account;
import com.abc.domain.account.CheckingAccount;
import com.abc.domain.account.CheckingAccountTestBuilder;
import com.abc.domain.sub.money.Money;

public class AccountStatementTest {

	@Test
	public void testGenerate() {
		Account checkingAccount = new CheckingAccountTestBuilder().deposit(Money.dollars(100)).withdraw(Money.dollars(10)).build();
		
		AccountStatement statement = new AccountStatement(checkingAccount);
		
		String expected = "Checking Account\n" +
						  " deposit $100.00\n" +
						  " withdrawal $10.00\n" +
						  "Total $90.00";
		
		assertThat(statement.generate(), is(expected));
	}
	
	@Test
	public void testDollarFormat() throws Exception {
		AccountStatement statement = new AccountStatement(new CheckingAccount());
		assertThat(statement.dollarFormat(Money.dollars(10.011)), is("$10.01"));
	}
}
