package com.abc;


public class NegativeOrZeroAmountException extends RuntimeException {
    public NegativeOrZeroAmountException() {
    }

    public NegativeOrZeroAmountException(String message) {
        super(message);
    }

    public NegativeOrZeroAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeOrZeroAmountException(Throwable cause) {
        super(cause);
    }

    public NegativeOrZeroAmountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
