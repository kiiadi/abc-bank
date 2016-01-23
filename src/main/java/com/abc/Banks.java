package com.abc;

//static class for maintaing the account number of the last account created
public class Banks {
    private static int lastAccountNumber = 1;

    private Banks() {};

    public static int getLastAccountNumber() {
        return lastAccountNumber;
    }

    public static void setLastAccountNumber(int number) {
        lastAccountNumber = number;
    }
}
