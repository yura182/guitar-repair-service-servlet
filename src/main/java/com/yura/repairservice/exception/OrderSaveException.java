package com.yura.repairservice.exception;

public class OrderSaveException extends RuntimeException {

    public OrderSaveException() {
    }

    public OrderSaveException(String message) {
        super(message);
    }

    public OrderSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
