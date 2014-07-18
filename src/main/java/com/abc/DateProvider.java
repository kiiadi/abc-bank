package com.abc;

public class DateProvider {

    protected static final long DAY = 24 * 3600 * 1000;

    private static final DateProvider instance = new DateProvider();

    public static DateProvider getInstance() {
        return instance;
    }

    public long now() {
        return System.currentTimeMillis();
    }

    public long periodInDays(int days) {
        return DAY * days;
    }
}
