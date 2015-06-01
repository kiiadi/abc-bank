package com.abc.transaction;

import java.util.Date;

public interface Transaction {

    String getName();

    double getAmount();

    Date getDate();

    double applyTo(double balance);
}
