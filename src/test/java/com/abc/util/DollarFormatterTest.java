package com.abc.util;

import junit.framework.Assert;
import org.junit.Test;

public class DollarFormatterTest {

    @Test
    public void shouldFormatWithDollar () throws Exception{
        String formattedResult = DollarFormatter.toDollars(1);
        Assert.assertEquals("$1.00",formattedResult);

    }

    @Test
    public void shouldFormatSingle () throws Exception{
        String formattedResult = DollarFormatter.format(1, "account");
        Assert.assertEquals("1 account",formattedResult);

    }

    @Test
    public void shouldFormatPlural () throws Exception{
        String formattedResult = DollarFormatter.format(2, "account");
        Assert.assertEquals("2 accounts",formattedResult);

    }
}
