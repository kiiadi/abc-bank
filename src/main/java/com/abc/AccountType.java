package com.abc;

public enum AccountType {

    Checking("Checking Account"),
    Savings("Savings Account"),
    MaxiSavings("Maxi Savings Account");

    AccountType(String description) {
        this.description = description;
    }
    private final String description;

    public String getDescription() {
        return description;
    }
}
