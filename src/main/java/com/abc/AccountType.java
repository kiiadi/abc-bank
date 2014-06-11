package com.abc;

/**
 * A type for an account.
 */
public enum AccountType {

    CHECKING("Checking"), SAVINGS("Savings"), MAXI_SAVINGS("Maxi Savings");

    private final String title;

    AccountType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
