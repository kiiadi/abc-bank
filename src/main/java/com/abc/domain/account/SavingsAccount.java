package com.abc.domain.account;

import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.money.Ratio;


public class SavingsAccount extends AbstractAccount {

    Ratio annualRate = Ratio.of(0.001);
    
    public SavingsAccount() {
        super("Savings Account");
    }
    
	@Override
	public Money interest() {
		if (!balance().isGreaterThan(Money.dollars(1000)))
			return balance().of(annualRate);
		
		return balance().minus(Money.dollars(1000)).times(0.002).plus(Money.dollars(1));
	}
	
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof SavingsAccount)) {
            return false;
        }

        SavingsAccount other = (SavingsAccount) object;
        
        return name().equals(other.name());
    }
}
