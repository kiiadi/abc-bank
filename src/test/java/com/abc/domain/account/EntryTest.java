package com.abc.domain.account;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.abc.domain.sub.money.Money;
import com.abc.domain.sub.time.TimePoint;

public class EntryTest {
    
    @Test
    public void testEquality() throws Exception {
        Entry one = new Entry(Money.dollars(100), TimePoint.at(2014, 3, 13, 10, 0));
        Entry another = new Entry(Money.dollars(100), TimePoint.at(2014, 3, 13, 10, 0));
        
        assertThat(one, is(another));
    }
    
    @Test
    public void testGet() throws Exception {
        Entry entry = new Entry(Money.dollars(100), TimePoint.at(2014, 3, 13, 10, 0));
        
        assertThat(entry.amount(), is(Money.dollars(100)));
        assertThat(entry.whenBooked(), is(TimePoint.at(2014, 3, 13, 10, 0)));
        assertThat(entry.name(), is("deposit"));
    }
}
