package com.abc.domain.account;

import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.money.Ratio;
import com.abc.domain.sub.time.DayOfYear;
import com.abc.domain.sub.time.Duration;

public class CheckingAccount extends AbstractAccount {
	
	private Ratio pointOnePercent = Ratio.of(0.001);
	
	DayOfYear today;
	
    public CheckingAccount() {
        super("Checking Account");
        
        today = DayOfYear.today();
    }
    
	@Override
	public Money interest() {
	    Money totalInterest = Money.dollars(0);
        for (Entry each : entries()) {
            Ratio daysAccruedRate = daysAccruedRate(each.whenBooked().asDayOfYear(), this.today);
            
            totalInterest = totalInterest.plus(each.amount().of(daysAccruedRate));
        }
        
	    return totalInterest.of(pointOnePercent);
	}
	
	Ratio daysAccruedRate(DayOfYear whenBooked, DayOfYear today) {
		return Ratio.of(dayElapsed(whenBooked, today), 365);
	}
	
    int dayElapsed(DayOfYear from, DayOfYear to) {
        return Duration.between(from, to).asDays();
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof CheckingAccount)) {
            return false;
        }

        CheckingAccount other = (CheckingAccount) object;
        
        return name().equals(other.name());
    }
}