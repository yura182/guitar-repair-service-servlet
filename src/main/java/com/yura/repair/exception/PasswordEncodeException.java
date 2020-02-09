package com.yura.repair.exception;

public class PasswordEncodeException extends RuntimeException {
    public PasswordEncodeException() {
    }

    public PasswordEncodeException(String message) {
        super(message);
    }

    public PasswordEncodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
