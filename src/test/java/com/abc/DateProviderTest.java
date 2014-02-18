package com.abc;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by serge on 2/13/14.
 */
public class DateProviderTest {
    private static final long ONE_DAY = 24*60*60*1000;

    @Test
    public void testNow() throws Exception {
        assertEquals(Calendar.getInstance().getTime().toString(),DateProvider.getInstance().now().toString());
    }

    @Test
    public void testSetFutureDateAndReset(){
        assertEquals(Calendar.getInstance().getTime().toString(),DateProvider.getInstance().now().toString());
        System.out.println(DateProvider.getInstance().now().toString());
        DateProvider.getInstance().setFutureDate(10);
        System.out.println("10 days later:"+DateProvider.getInstance().now().toString());
        Date date = new Date(Calendar.getInstance().getTimeInMillis() + 10*ONE_DAY);
        assertEquals(date.toString(), DateProvider.getInstance().now().toString());
        DateProvider.getInstance().reset();
        assertEquals(Calendar.getInstance().getTime().toString(), DateProvider.getInstance().now().toString());
        System.out.println("after reset: "+DateProvider.getInstance().now().toString());
    }

    @Test
    public void testSetFutureDateAndResetAsPastDate(){
        assertEquals(Calendar.getInstance().getTime().toString(),DateProvider.getInstance().now().toString());
        System.out.println(DateProvider.getInstance().now().toString());
        DateProvider.getInstance().setFutureDate(-10);
        System.out.println("10 days before: "+DateProvider.getInstance().now().toString());
        Date date = new Date(Calendar.getInstance().getTimeInMillis() - 10*ONE_DAY);
        assertEquals(date.toString(), DateProvider.getInstance().now().toString());
        DateProvider.getInstance().reset();
        assertEquals(Calendar.getInstance().getTime().toString(), DateProvider.getInstance().now().toString());
        System.out.println("after reset: "+DateProvider.getInstance().now().toString());
    }
}
