package com.abc.domain.account.transaction;

import com.abc.domain.account.Account;
import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.time.Clock;

public class Withdrawal extends Transaction {

	public Withdrawal(Money amount, Account from, Clock clock) {
		super(amount, clock);
		from.withdraw(amount, clock.now());
	}
	
	@Override
	public String name() {
		return "withdrawal";
	}
	
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Withdrawal)) {
            return false;
        }

        Withdrawal other = (Withdrawal) object;
        
        return this.amount.equals(other.amount) && this.whenOccured.equals(other.whenOccured);
    }
}
