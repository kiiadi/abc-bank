package com.abc.domain.account.transaction;

import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.time.Clock;
import com.abc.domain.sub.time.TimePoint;

public abstract class Transaction {
	
	protected Money amount;
	protected TimePoint whenOccured;

	public Transaction(Money amount, Clock clock) {
		this.amount = amount;
		this.whenOccured = clock.now();
	}
	
	public Money getAmount() {
		return amount;
	}
	
	public TimePoint getWhenOccured() {
		return whenOccured;
	}
	
	public abstract String name();

    @Override
    public int hashCode() {
        int result = 17;
        
        result = 37 * result + amount.hashCode();
        result = 37 * result + whenOccured.hashCode();

        return result;
    }
    
    @Override
    public String toString() {
    	return name() + " transaction: amount [" + amount + "] at " + whenOccured;
    }
}
