package com.abc;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: suppi
 * Date: 6/25/14
 * Time: 12:55 AM
 * To change this template use File | Settings | File Templates.
 */
public enum AccountType {


    CHECKING {
        BigDecimal interestEarned(BigDecimal balance) {
            return CurrencyUtil.convertToCurrency(balance.multiply(new BigDecimal(0.001)));

        }


    },
    SAVINGS {
        BigDecimal interestEarned(BigDecimal balance) {
            BigDecimal value;
            if (balance.compareTo(new BigDecimal(1000)) !=1 )
                value = balance.multiply(new BigDecimal(0.001));
            else {
                value = new BigDecimal(1).add(balance.subtract(new BigDecimal(1000)).multiply(new BigDecimal(0.002)));
            }
            return CurrencyUtil.convertToCurrency(value);
        }

    },
    MAXI_SAVINGS {

        BigDecimal interestEarned(BigDecimal balance) {
            BigDecimal value;
            if (balance.compareTo(ONE_THOUSAND) !=1 )
                value =  balance.multiply(new BigDecimal(0.02));
            else if (balance.compareTo(TWO_THOUSAND) !=1)
                value =  TWENTY.add(balance.subtract(ONE_THOUSAND).multiply(new BigDecimal(0.05)));
            else {
                value =  SEVENTY.add(balance.subtract(TWO_THOUSAND).multiply(new BigDecimal(0.1)));
            }
            return CurrencyUtil.convertToCurrency(value);
        }

    };
    private static final BigDecimal ONE_THOUSAND = new BigDecimal(1000);
    private static final BigDecimal TWO_THOUSAND = new BigDecimal(2000);
    private static final BigDecimal TWENTY = new BigDecimal(20);
    private static final BigDecimal SEVENTY = new BigDecimal(70);



    abstract BigDecimal interestEarned(BigDecimal balance);
}
