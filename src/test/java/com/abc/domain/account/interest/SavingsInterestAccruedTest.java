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

public class SavingsInterestAccruedTest {
    
    Clock mockedClock;
    
    @Before
    public void setUp() throws Exception {
        mockedClock = mock(Clock.class);
        when(mockedClock.now()).thenReturn(TimePoint.at(2014, 3, 13, 22, 00));
    }
    
    /**
     * $182.50 * (12 / 365) * 0.001 = $0.01 (rounding of $0.006)
     */
    @Test
    public void testAccuredAmountOneEntryLessThanEqualsTo1000Dollars() throws Exception {
        Accrued mockedAccruedRate = mock(Accrued.class);
        when(mockedAccruedRate.rate(DayOfYear.at(2014, 3, 1), DayOfYear.at(2014, 3, 13))).thenReturn(Ratio.of(12, 365));
        
        SavingsInterestAccrued accrued = new SavingsInterestAccrued();
        accrued.accruedRate = mockedAccruedRate;
        accrued.clock = mockedClock;
        
        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(Money.dollars(182.5), DayOfYear.at(2014, 3, 1).asTimePoint())); 
        
        assertThat(accrued.amount(entries), is(Money.dollars(0.01)));
    }
    
    /**
     * first $1000.00 * (12 / 365) * 0.001 = $0.03
     * second $1000.00 * (12 / 365) * 0.002 = $0.07
     */
    @Test
    public void testAccuredAmountOneEntryMoreThan1000Dollars() throws Exception {
        Accrued mockedAccruedRate = mock(Accrued.class);
        when(mockedAccruedRate.rate(DayOfYear.at(2014, 3, 1), DayOfYear.at(2014, 3, 13))).thenReturn(Ratio.of(12, 365));
        
        SavingsInterestAccrued accrued = new SavingsInterestAccrued();
        accrued.accruedRate = mockedAccruedRate;
        accrued.clock = mockedClock;
        
        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(Money.dollars(2000), DayOfYear.at(2014, 3, 1).asTimePoint())); 
        
        assertThat(accrued.amount(entries), is(Money.dollars(0.10)));
    }
    
    /**
     * $182.50 * (12 / 365) * 0.001 =  $0.01
     * $365.00 * (30 / 365) * 0.001 =  $0.03
     */
    @Test
    public void testAccuredAmountTwoEntries() throws Exception {
        Accrued mockedAccruedRate = mock(Accrued.class);
        when(mockedAccruedRate.rate(DayOfYear.at(2014, 3, 1), DayOfYear.at(2014, 3, 13))).thenReturn(Ratio.of(12, 365));
        when(mockedAccruedRate.rate(DayOfYear.at(2014, 2, 11), DayOfYear.at(2014, 3, 13))).thenReturn(Ratio.of(30, 365));
        
        SavingsInterestAccrued accrued = new SavingsInterestAccrued();
        accrued.accruedRate = mockedAccruedRate;
        accrued.clock = mockedClock;

        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(Money.dollars(182.5), DayOfYear.at(2014, 3, 1).asTimePoint()));  
        entries.add(new Entry(Money.dollars(365), DayOfYear.at(2014, 2, 11).asTimePoint()));
        
        assertThat(accrued.amount(entries), is(Money.dollars(0.04)));
    }
}
