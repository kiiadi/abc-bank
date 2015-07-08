package com.abc;

import static java.lang.Math.abs;

public final class Utils {

  public static String toDollars(double d) {
    return String.format("$%,.2f", abs(d));
  }

  private Utils() {
  }
}
