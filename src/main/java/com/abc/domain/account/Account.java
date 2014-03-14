package com.abc.domain.account;

import java.util.List;

import com.abc.domain.statement.Statement;
import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.time.TimePoint;

public interface Account {

	void deposit(Money amount, TimePoint timePoint);
	
	void withdraw(Money amount, TimePoint timePoint);
	
	Money balance();
	
	Money interest();

	String name();
	
	Statement statement();
	
	List<Entry> entries();
}
