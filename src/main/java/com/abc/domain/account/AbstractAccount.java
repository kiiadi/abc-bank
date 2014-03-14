package com.abc.domain.account;

import java.util.ArrayList;
import java.util.List;

import com.abc.domain.account.interest.Interest;
import com.abc.domain.statement.AccountStatement;
import com.abc.domain.statement.Statement;
import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.time.TimePoint;

public abstract class AbstractAccount implements Account {
	
    private String name;
	private Money balance;
	protected Interest interest;
	private Statement accountStatement;
	
	protected List<Entry> entries;
	
	public AbstractAccount(String name) {
	    this.name = name;
		this.entries = new ArrayList<Entry>();
		this.balance = Money.dollars(0);
		this.accountStatement = new AccountStatement(this);
	}

	@Override
	public void deposit(Money amount, TimePoint timePoint) {
		balance = balance.plus(amount);
		entries.add(new Entry(amount, timePoint));
	}
	
	@Override
	public void withdraw(Money amount, TimePoint timePoint) {
		if (balance.isLessThan(amount)) {
			throw new InSufficientAmountException();
		}
		
		balance = balance.minus(amount);
		entries.add(new Entry(amount.negate(), timePoint));
	}
	
	@Override
	public String name() {
	    return name;
	}
	
	@Override
	public Money balance() {
		return balance;
	}
	
	@Override
	public Money interest() {
	    return interest.amount(entries());
	}

	@Override
	public Statement statement() {
		return accountStatement;
	}
	
	@Override
	public List<Entry> entries() {
		return entries;
	}
    
    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + name().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "account: " + name() + ", balance: " + balance();
    }
}
