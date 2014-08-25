package com.abc.api;

public enum AccountType {
    checking("Checking Account"),
    savings("Savings Account"),
    maxi("Maxi-Savings Account");

    String description;

    AccountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "description='" + description + '\'' +
                '}';
    }
}
