package com.abc.domain.sub.time;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TimeOfDayTest {

	@Test
    public void testCreateInstance() throws Exception {
        assertThat(TimeOfDay.atMidnight(), is(TimeOfDay.at(0, 0)));
        assertThat(TimeOfDay.atNoon(), is(TimeOfDay.at(12, 0)));
    }
    
    @Test
    public void testEquals() throws Exception {
        assertThat(TimeOfDay.at(12, 30), is(TimeOfDay.at(12, 30)));
    }
    
    @Test
    public void testComapreTo() throws Exception {
        assertThat(TimeOfDay.at(12, 30).compareTo(TimeOfDay.at(12, 31)), is(-1));
        assertThat(TimeOfDay.at(12, 30).compareTo(TimeOfDay.at(12, 30)), is(0));
        assertThat(TimeOfDay.at(12, 30).compareTo(TimeOfDay.at(12, 29)), is(1));
    }
    
    @Test
    public void testIsBefore() throws Exception {
        assertThat(TimeOfDay.atMidnight().isBefore(TimeOfDay.atNoon()), is(true));
        assertThat(TimeOfDay.at(12, 30, 10).isBefore(TimeOfDay.at(13, 20, 15)), is(true));
        assertThat(TimeOfDay.at(12, 30, 10).isBefore(TimeOfDay.at(13, 30, 10, 500)), is(true));
    }
    
    @Test
    public void testIsAfter() throws Exception {
        assertThat(TimeOfDay.atNoon().isAfter(TimeOfDay.atMidnight()), is(true));
        assertThat(TimeOfDay.at(13, 20, 15).isAfter(TimeOfDay.at(12, 30, 10)), is(true));
        assertThat(TimeOfDay.at(12, 30, 10).isAfter(TimeOfDay.at(12, 30, 9, 500)), is(true));
    }
}
