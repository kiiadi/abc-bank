package com.abc;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created with IntelliJ IDEA.
 * User: suppi
 * Date: 6/25/14
 * Time: 3:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class CurrencyUtil {

    public static BigDecimal convertToCurrency(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_EVEN);
    }
}
