package com.abc.transaction;

import java.util.Date;

public interface DateProvider {

    Date now();

    Date now(int plusDays);
}
