package com.yura.repair.exception;

public class InvalidInstrumentParameterException extends RuntimeException {
    public InvalidInstrumentParameterException() {
    }

    public InvalidInstrumentParameterException(String message) {
        super(message);
    }

    public InvalidInstrumentParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
