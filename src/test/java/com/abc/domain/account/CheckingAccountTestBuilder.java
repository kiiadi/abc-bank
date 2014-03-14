package com.abc.domain.account;

import java.util.ArrayList;
import java.util.List;

import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.time.TimePoint;

public class CheckingAccountTestBuilder {

	private List<Money> deposits;
	
	private List<Money> withdraws;
	
	public CheckingAccountTestBuilder() { 
		deposits = new ArrayList<Money>();
		withdraws = new ArrayList<Money>();
	}

	public CheckingAccountTestBuilder deposit(Money amount) {
		this.deposits.add(amount);
		return this;
	}
	
	public CheckingAccountTestBuilder withdraw(Money amount) {
		this.withdraws.add(amount);
		return this;
	}

	public Account build() {
		Account account = new CheckingAccount();
		
		for (Money each : deposits) {
			account.deposit(each, TimePoint.now());
		}
		
		for (Money each : withdraws) {
			account.withdraw(each, TimePoint.now());
		}
		
		return account;
	}
}
