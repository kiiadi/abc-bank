package com.abc.domain.account;

import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.money.Ratio;
import com.abc.domain.sub.time.DayOfYear;
import com.abc.domain.sub.time.TimePoint;


public class MaxiSavingsAccount extends AbstractAccount {
	
	private Ratio fivePercentRate = Ratio.of(0.05);
	private Ratio pointOnePercentRate = Ratio.of(0.001);
	
	TimePoint now;
	
    public MaxiSavingsAccount() {
        super("Maxi Savings Account");
        
        now = TimePoint.now();
    }
    
	@Override
	public Money interest() {
		DayOfYear tenDaysAgo = now.asDayOfYear().rollDay(-10);
		
		if (noWithdrawalHappened() || getLastWithdrawalDay().isBefore(tenDaysAgo))
			return balance().of(fivePercentRate);
		
		return balance().of(pointOnePercentRate);
	}
	
	private boolean noWithdrawalHappened() {
		return lastWithdraw == null;
	}
	
	DayOfYear getLastWithdrawalDay() {
		return lastWithdraw.whenBooked().asDayOfYear();
	}
	
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof MaxiSavingsAccount)) {
            return false;
        }

        MaxiSavingsAccount other = (MaxiSavingsAccount) object;
        
        return name().equals(other.name());
    }
}