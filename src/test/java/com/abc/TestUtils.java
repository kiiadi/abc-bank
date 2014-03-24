package com.abc;


import org.junit.Assert;

import java.math.BigDecimal;

public class TestUtils {

    public static void assertEquals(BigDecimal b1, BigDecimal b2) {
        if (b1 != null && b2 == null) {
            Assert.fail();
        }
        if (b1 == null && b2 != null) {
            Assert.fail();
        }
        if (b1 != null) {
            if (b1.compareTo(b2) != 0) {
                Assert.fail("Expected: " + b1 + ", got: " + b2);
            }
        }
    }
}
