package com.yura.repair.exception;

public class DBRuntimeException extends RuntimeException {
    public DBRuntimeException() {
    }

    public DBRuntimeException(String message) {
        super(message);
    }

    public DBRuntimeException(String message, Throwable cause) {
    }
}
