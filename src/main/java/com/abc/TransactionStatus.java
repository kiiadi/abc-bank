package com.abc;

public class TransactionStatus {
    private boolean completed;
    private String message;
    
     public TransactionStatus(boolean complet, String msg) {
        completed = complet;
        message = msg;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getMessage() {
        return message;
    }
}
