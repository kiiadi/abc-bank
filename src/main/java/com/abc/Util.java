package com.abc;

import static java.lang.Math.abs;

/**
 * @author Adam
 *         Date: 5/12/14
 *         Time: 6:53 PM
 */
public final class Util {

  private Util() {
  }

  public static String toDollars(double amount) {
    if (amount < 0)
      return String.format("-$%,.2f", abs(amount));
    else
      return String.format("$%,.2f", abs(amount));
  }
}
