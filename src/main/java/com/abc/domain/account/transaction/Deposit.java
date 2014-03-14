package com.abc.domain.account.transaction;

import com.abc.domain.account.Account;
import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.time.Clock;

public class Deposit extends Transaction {

	public Deposit(Money amount, Account to, Clock clock) {
		super(amount, clock);
		to.deposit(amount, clock.now());
	}

	@Override
	public String name() {
		return "deposit";
	}
	
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Deposit)) {
            return false;
        }

        Deposit other = (Deposit) object;
        
        return this.amount.equals(other.amount) && this.whenOccured.equals(other.whenOccured);
    }
}
