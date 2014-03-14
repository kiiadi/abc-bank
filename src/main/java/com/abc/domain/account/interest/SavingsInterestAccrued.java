package com.abc.domain.account.interest;

import java.util.List;

import com.abc.domain.account.Entry;
import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.money.Ratio;

public class SavingsInterestAccrued extends AbstractInterestAccrued {
    
    private Ratio oneThousandth = Ratio.of(0.001);
    private Ratio twoThousandth = Ratio.of(0.002);
    
    @Override
    public Money amount(List<Entry> entries) {
        Money totalInterest = Money.dollars(0);
        Money uptoBalance = Money.dollars(0);
        
        for (Entry each : entries) {
            uptoBalance = uptoBalance.plus(each.amount());
            
            Money accuredInterestAmount = Money.dollars(0);
            if (uptoBalance.isGreaterThan(Money.dollars(1000))) {
                accuredInterestAmount = caluateAccruedInterestGreaterThan1000DollarsBalance(each) ;
            }
            else {
                accuredInterestAmount = caluateAccruedInterestLessThanEqualTo1000DollarsBalance(each) ;
            }
            
            totalInterest = totalInterest.plus(accuredInterestAmount);
        }
        
        return totalInterest;
    }
    
    /**
     * TODO: accrued calculation logic seems wrong. need to clarify.
     */
    Money caluateAccruedInterestGreaterThan1000DollarsBalance(Entry entry) {
        Entry firstThousand = new Entry(Money.dollars(1000), entry.whenBooked());
        Entry rest = new Entry(entry.amount().minus(Money.dollars(1000)), entry.whenBooked());
        
        Money interstForFirstThousand = caluateAccruedInterestLessThanEqualTo1000DollarsBalance(firstThousand);
        Money interstForRest = accruedAmount(rest).of(twoThousandth);
                        
        return interstForFirstThousand.plus(interstForRest);
    }
    
    Money caluateAccruedInterestLessThanEqualTo1000DollarsBalance(Entry entry) {
        return accruedAmount(entry).of(oneThousandth);
    }
}
