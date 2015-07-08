package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
  public interface Provider {
    Date now();
  }

  private static Provider instance = null;

  public static Provider getInstance() {
    if (instance == null) {
      instance = new DefaultDateProvider();
    }
    return instance;
  }

  public static void setInstance(Provider instance) {
    DateProvider.instance = instance;
  }

  private static class DefaultDateProvider implements Provider {

    public Date now() {
      return Calendar.getInstance().getTime();
    }
  }
}