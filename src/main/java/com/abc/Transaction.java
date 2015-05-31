package com.abc;

import java.util.Date;

public interface Transaction {

    String getName();

    double getAmount();

    Date getDate();

    double applyTo(double balance);
}
