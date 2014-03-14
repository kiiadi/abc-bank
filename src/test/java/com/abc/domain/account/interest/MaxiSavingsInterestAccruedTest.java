package com.abc.domain.account.interest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
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

public class MaxiSavingsInterestAccruedTest {
    
    Clock mockedClock;
    
    @Before
    public void setUp() throws Exception {
        mockedClock = mock(Clock.class);
        when(mockedClock.now()).thenReturn(TimePoint.at(2014, 3, 13, 22, 00));
    }
    
    @Test
    public void testWhenLastWithdrawed() throws Exception {
        MaxiSavingsInterestAccrued accrued = new MaxiSavingsInterestAccrued();

        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(Money.dollars(10), DayOfYear.at(2014, 3, 1).asTimePoint())); 
        assertThat(accrued.whenLastWithdrawed(entries), is(nullValue()));
        
        entries.add(new Entry(Money.dollars(10).negate(), DayOfYear.at(2014, 3, 2).asTimePoint())); 
        entries.add(new Entry(Money.dollars(10), DayOfYear.at(2014, 3, 3).asTimePoint())); 
        assertThat(accrued.whenLastWithdrawed(entries), is(DayOfYear.at(2014, 3, 2)));
    }
    
    @Test
    public void testNoWithdrawalFor10Days() throws Exception {
        MaxiSavingsInterestAccrued accrued = new MaxiSavingsInterestAccrued();
        accrued.clock = mockedClock;
        
        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(Money.dollars(10).negate(), DayOfYear.at(2014, 3, 2).asTimePoint())); 
        assertThat(accrued.noWithdrawalFor10Days(entries), is(true));
        
        entries.add(new Entry(Money.dollars(10).negate(), DayOfYear.at(2014, 3, 3).asTimePoint())); 
        assertThat(accrued.noWithdrawalFor10Days(entries), is(false));
    }
    
    /**
     * $182.50 * (12 / 365) =  $6.00
     * $6.00 * 0.05 = $0.30
     */
    @Test
    public void testAccuredAmountOneEntryNoWithdrawal() throws Exception {
        Accrued mockedAccruedRate = mock(Accrued.class);
        when(mockedAccruedRate.rate(DayOfYear.at(2014, 3, 1), DayOfYear.at(2014, 3, 13))).thenReturn(Ratio.of(12, 365));
        
        MaxiSavingsInterestAccrued accrued = new MaxiSavingsInterestAccrued();
        accrued.accruedRate = mockedAccruedRate;
        accrued.clock = mockedClock;
        
        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(Money.dollars(182.5), DayOfYear.at(2014, 3, 1).asTimePoint())); 
        
        assertThat(accrued.amount(entries), is(Money.dollars(0.30)));
    }
}
