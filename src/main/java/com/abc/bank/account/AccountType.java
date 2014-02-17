package com.abc.bank.account;

public enum AccountType {

    checking(){
        @Override
        public String toString(){
            return "Checking Account";
        }
    },
    savings(){
        @Override
        public String toString(){
            return "Savings Account";
        }
    },
    maxiSavings(){
        @Override
        public String toString(){
            return "Maxi Savings Account";
        }
    }
}