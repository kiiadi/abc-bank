package com.abc.domain.statement;

import static java.lang.Math.abs;

import com.abc.domain.sub.money.Money;

public abstract class AbstractStatement implements Statement {

	protected static final String NEW_LINE = "\n";
	
	protected String dollarFormat(Money money) {
		return String.format("$%,.2f", abs(money.getAmount().doubleValue()));
	}
}
