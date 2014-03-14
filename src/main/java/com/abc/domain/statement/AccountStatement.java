package com.abc.domain.statement;

import com.abc.domain.account.Account;
import com.abc.domain.account.Entry;

public class AccountStatement extends AbstractStatement {

	private Account account;
	
	public AccountStatement(Account account) {
		this.account = account;
	}
	
	@Override
	public String generate() {
		String statement = account.name() + NEW_LINE;
		
		for (Entry entry : account.entries()) {
			statement += " " + entry.name() + " " + dollarFormat(entry.amount()) + NEW_LINE;
		}
		
		statement += "Total " + dollarFormat(account.balance());
		
		return statement;
	}
}
