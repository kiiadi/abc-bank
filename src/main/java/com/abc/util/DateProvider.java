package com.abc.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Archana on 9/14/14.
 */
public enum DateProvider {
  INSTANCE;
  public Date now() {
    return Calendar.getInstance().getTime();
  }


}
