package com.abc.domain.account.transaction;

import com.abc.domain.account.Account;
import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.time.Clock;

public class Transfer extends Transaction {

	public Transfer(Money amount, Account from, Account to, Clock clock) {
		super(amount, clock);
		
		from.withdraw(amount, clock.now());
		to.deposit(amount, clock.now());
	}

	@Override
	public String name() {
		return "transfer";
	}
}
