package com.abc;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by Yi on 12/16/15.
 */
public class DataProviderTest {
    @Test
    public void testGetInstance() {
        assertTrue(DateProvider.getInstance().now() instanceof Date);
    }
}
