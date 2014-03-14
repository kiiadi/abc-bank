package com.abc.domain.account.interest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.abc.domain.account.Entry;
import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.money.Ratio;
import com.abc.domain.sub.time.Clock;
import com.abc.domain.sub.time.DayOfYear;
import com.abc.domain.sub.time.TimePoint;

public class CheckingInterestAccruedTest {
    
    Clock mockedClock;
    
    @Before
    public void setUp() throws Exception {
        mockedClock = mock(Clock.class);
        when(mockedClock.now()).thenReturn(TimePoint.at(2014, 3, 13, 22, 00));
    }
    
    /**
     * $182.50 * (12 / 365) =  $6.00
     * $6.00 * 0.001 = $0.01 (rounding of $0.006)
     */
    @Test
    public void testAccuredAmountOneEntry() throws Exception {
        Accrued mockedAccruedRate = mock(Accrued.class);
        when(mockedAccruedRate.rate(DayOfYear.at(2014, 3, 1), DayOfYear.at(2014, 3, 13))).thenReturn(Ratio.of(12, 365));
        
        CheckingInterestAccrued accrued = new CheckingInterestAccrued();
        accrued.accruedRate = mockedAccruedRate;
        accrued.clock = mockedClock;
        
        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(Money.dollars(182.5), DayOfYear.at(2014, 3, 1).asTimePoint())); 
        
        assertThat(accrued.amount(entries), is(Money.dollars(0.01)));
    }
    
    /**
     * $182.50 * (12 / 365) =  $6.00
     * $3,650.00 * (30 / 365) =  $300.00
     * 
     * $306 * 0.001 = $0.31 (rounding of $0.306)
     */
    @Test
    public void testAccuredAmountTwoEntries() throws Exception {
        Accrued mockedAccruedRate = mock(Accrued.class);
        when(mockedAccruedRate.rate(DayOfYear.at(2014, 3, 2), DayOfYear.at(2014, 3, 13))).thenReturn(Ratio.of(12, 365));                
        when(mockedAccruedRate.rate(DayOfYear.at(2014, 2, 11), DayOfYear.at(2014, 3, 13))).thenReturn(Ratio.of(30, 365));
        
        CheckingInterestAccrued accrued = new CheckingInterestAccrued();
        accrued.accruedRate = mockedAccruedRate;
        accrued.clock = mockedClock;
                        
        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(Money.dollars(182.5), DayOfYear.at(2014, 3, 2).asTimePoint()));  
        entries.add(new Entry(Money.dollars(3650), DayOfYear.at(2014, 2, 11).asTimePoint()));
        
        assertThat(accrued.amount(entries), is(Money.dollars(0.31)));
    }
}
